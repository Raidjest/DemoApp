package com.example.demoapp.data;

public class User { //класс модели данных пользователя
    private String email;
    private String password;

    public User(String email, String password) { //конструктор класса данных пользователя
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}