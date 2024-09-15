package com.ms.back.auth.token;

import lombok.Data;

@Data
public class TokenRequest {

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
