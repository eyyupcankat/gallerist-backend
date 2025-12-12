package com.eyyupcankat.gallerist.service;

import com.eyyupcankat.gallerist.dto.AuthRequest;
import com.eyyupcankat.gallerist.dto.AuthResponse;
import com.eyyupcankat.gallerist.dto.DtoUser;
import com.eyyupcankat.gallerist.dto.RefreshTokenRequest;

public interface IAuthenticationService {

    public DtoUser register(AuthRequest authRequest);

    public AuthResponse authenticate(AuthRequest authRequest);

    public AuthResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

}
