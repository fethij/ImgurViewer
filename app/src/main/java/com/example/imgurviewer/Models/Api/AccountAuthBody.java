package com.example.imgurviewer.Models.Api;

public class AccountAuthBody {

    final String refresh_token;
    final String client_id;
    final String client_secret;
    final String grant_type;

    public AccountAuthBody(String refresh_token, String client_id, String client_secret, String grant_type) {
        this.refresh_token = refresh_token;
        this.client_id = client_id;
        this.client_secret = client_secret;
        this.grant_type = grant_type;
    }
}
