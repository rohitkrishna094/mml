package com.rohit.mml.payload.response;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private int status = 404;

    public JwtResponse(String token, int status) {
        this.token = token;
        this.status = status;
    }

    public JwtResponse(String accessToken) {
        this.token = accessToken;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return this.status;
    }

}
