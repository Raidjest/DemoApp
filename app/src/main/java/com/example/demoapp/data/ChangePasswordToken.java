package com.example.demoapp.data;

public class ChangePasswordToken { //класс модели данных токена для смены пароля

    String type;

    String email;

    String token;

    public ChangePasswordToken(String type, String email, String token) { //конструктор класса токена
        this.type = type;
        this.email = email;
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}