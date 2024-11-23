package com.example.demoapp.controller;

import com.example.demoapp.data.ChangePasswordToken;
import com.example.demoapp.data.Email;
import com.example.demoapp.data.ResponseUser;
import com.example.demoapp.data.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface API {

    //регистрация юзера
    @POST("signup")
    Call<ResponseUser> signUpByEmailAndPswrd(@Header("apikey") String apikey, @Body User user);

    //авторизация юзера
    @POST("token")
    Call<ResponseUser> login(@Query ("grant_type") String grant_type, @Header("apikey") String apikey, @Body User user);

    //отправка кода на почту для восстановления пароля
    @POST("recover")
    Call<Void> sendCode(@Header("apikey") String apikey, @Body Email email);

    //верификация введенного OTP
    @POST("verify")
    Call<ResponseUser> verifyCode(@Header("apikey") String apikey, @Body ChangePasswordToken changePasswordToken);

    //обновление пароля пользователя
    @PUT("user")
    Call<Void> updatePassword(@Header("apikey") String apikey, @Header("Authorization") String token, @Body User user);


}