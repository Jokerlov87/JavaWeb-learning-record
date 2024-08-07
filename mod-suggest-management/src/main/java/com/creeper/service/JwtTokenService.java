package com.creeper.service;

import com.creeper.pojo.JwtToken;

public interface JwtTokenService {
    void saveToken(JwtToken jwtToken);

    JwtToken getAdminTokenByToken(String token);
}
