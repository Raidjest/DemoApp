package com.example.demoapp.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseUser { //класс полей для обращения к базе

    @SerializedName("access_token")
    @Expose
    public String accessToken;
    @SerializedName("token_type")
    @Expose
    public String tokenType;
    @SerializedName("expires_in")
    @Expose
    public Integer expiresIn;
    @SerializedName("expires_at")
    @Expose
    public Integer expiresAt;
    @SerializedName("refresh_token")
    @Expose
    public String refreshToken;


}