package com.eyyupcankat.gallerist.controller;

import com.eyyupcankat.gallerist.dto.AuthRequest;
import com.eyyupcankat.gallerist.dto.AuthResponse;
import com.eyyupcankat.gallerist.dto.DtoUser;
import com.eyyupcankat.gallerist.dto.RefreshTokenRequest;

public interface IAuthenticationController {

    public ControllerResponse<DtoUser> register(AuthRequest authRequest);

    public ControllerResponse<AuthResponse> authenticate(AuthRequest authRequest);

    public ControllerResponse<AuthResponse> refreshToken(RefreshTokenRequest refreshTokenRequest);

}
