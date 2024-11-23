package com.example.demoapp.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Email { //класс обработки почты пользователя

    @SerializedName("email")
    @Expose
    private String email;

    public Email(String email) {
        this.email = email;
    }
}
