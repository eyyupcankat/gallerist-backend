package com.eyyupcankat.gallerist.controller.impl;

import com.eyyupcankat.gallerist.controller.BaseController;
import com.eyyupcankat.gallerist.controller.ControllerResponse;
import com.eyyupcankat.gallerist.controller.IAuthenticationController;
import com.eyyupcankat.gallerist.dto.AuthRequest;
import com.eyyupcankat.gallerist.dto.AuthResponse;
import com.eyyupcankat.gallerist.dto.DtoUser;
import com.eyyupcankat.gallerist.dto.RefreshTokenRequest;
import com.eyyupcankat.gallerist.service.IAuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationControllerImpl extends BaseController implements IAuthenticationController {


    @Autowired
    private IAuthenticationService authenticationService;

    @PostMapping("/register")
    @Override
    public ControllerResponse<DtoUser> register(@Valid @RequestBody AuthRequest authRequest) {
        return ok(authenticationService.register(authRequest));
    }

    @PostMapping("/authenticate")
    @Override
    public ControllerResponse<AuthResponse> authenticate(@Valid @RequestBody AuthRequest authRequest) {
        return ok(authenticationService.authenticate(authRequest));
    }

    @PostMapping("/refresh-token")
    @Override
    public ControllerResponse<AuthResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ok(authenticationService.refreshToken(refreshTokenRequest));
    }
}
