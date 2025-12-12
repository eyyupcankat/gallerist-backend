package com.eyyupcankat.gallerist.service.impl;

import com.eyyupcankat.gallerist.dto.AuthRequest;
import com.eyyupcankat.gallerist.dto.AuthResponse;
import com.eyyupcankat.gallerist.dto.DtoUser;
import com.eyyupcankat.gallerist.dto.RefreshTokenRequest;
import com.eyyupcankat.gallerist.entity.RefreshToken;
import com.eyyupcankat.gallerist.entity.User;
import com.eyyupcankat.gallerist.exception.BaseException;
import com.eyyupcankat.gallerist.exception.ErrorMessage;
import com.eyyupcankat.gallerist.exception.MessageType;
import com.eyyupcankat.gallerist.jwt.JwtService;
import com.eyyupcankat.gallerist.repository.RefreshTokenRepository;
import com.eyyupcankat.gallerist.repository.UserRepository;
import com.eyyupcankat.gallerist.service.IAuthenticationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;


    private User createUser(AuthRequest authRequest) {
        User user = new User();
        user.setUsername(authRequest.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(authRequest.getPassword()));
        user.setCreateTime(new Date());
        return user;
    }

    @Override
    public DtoUser register(AuthRequest authRequest) {
        DtoUser dtoUser = new DtoUser();

        User savedUser = userRepository.save(createUser(authRequest));

        BeanUtils.copyProperties(savedUser, dtoUser);

        return dtoUser;
    }


    private RefreshToken  createRefreshToken(User user) {
        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setCreateTime(new Date());
        refreshToken.setExpiredDate(new Date(System.currentTimeMillis() + 1000*60*60*4));
        refreshToken.setRefreshToken(UUID.randomUUID().toString());
        refreshToken.setUser(user);

        return refreshToken;
    }

    @Override
    public AuthResponse authenticate(AuthRequest authRequest) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword());

            authenticationProvider.authenticate(authenticationToken);

            Optional<User> optionalUser = userRepository.findByUsername(authRequest.getUsername());

            String accessToken = jwtService.generateToken(optionalUser.get());

            RefreshToken savedRefreshToken = refreshTokenRepository.save(createRefreshToken(optionalUser.get()));

            return  new AuthResponse(accessToken, savedRefreshToken.getRefreshToken());
        }catch (Exception e){
            throw new BaseException(new ErrorMessage(MessageType.USERNAME_OR_PASSWORD_INVALID, e.getMessage()));
        }
    }

    public boolean isRefreshTokenValid(Date expireDate) {
        return expireDate.after(new Date());
    }

    @Override
    public AuthResponse refreshToken(RefreshTokenRequest  refreshTokenRequest) {
        Optional<RefreshToken> optionalRefreshToken = refreshTokenRepository.findByRefreshToken(refreshTokenRequest.getRefreshToken());

        if (optionalRefreshToken.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.REFRESH_TOKEN_NOT_FOUND, refreshTokenRequest.getRefreshToken()));
        }
        if (!isRefreshTokenValid(optionalRefreshToken.get().getExpiredDate())){
            throw new BaseException(new ErrorMessage(MessageType.REFRESH_TOKEN_IS_EXPIRED, refreshTokenRequest.getRefreshToken()));
        }

        String accessToken = jwtService.generateToken(optionalRefreshToken.get().getUser());
        RefreshToken newRefreshToken = refreshTokenRepository.save(createRefreshToken(optionalRefreshToken.get().getUser()));

        return  new AuthResponse(accessToken, newRefreshToken.getRefreshToken());
    }



}
