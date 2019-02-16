package com.apple.selfone.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtAuthenticationReponse {
    private String accessToken;
    private String tokenType = "Bearer";

    public JwtAuthenticationReponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
