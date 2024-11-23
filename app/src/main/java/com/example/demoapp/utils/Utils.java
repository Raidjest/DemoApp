package com.example.demoapp.utils;
import com.example.demoapp.data.User;

public class Utils {
    //API облачного хранилища
    public static final String APIKEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InNmbm9hZWFza25kaXRjdHJsZnpzIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MzIwODc1NjksImV4cCI6MjA0NzY2MzU2OX0.ipWHhRDsYTV1ywUiui3BuCKTUn9wuiwa9FqHf_-qr1g";

    //тип используемых данных
    public static final String CONTENT_TYPE = "application/json";
    //экземпляр пользователя с пустыми данными
    public static User user = new User("","");
    //ссылка обработки аутентификации в облачном хранилище
    public static final String BASE_URL = "https://sfnoaeasknditctrlfzs.supabase.co/auth/v1/";
    //тип предоставляемых данных
    public static final String GRANT_TYPE = "password";

    //токен доступа пользователя
    public static String TOKEN = "";
}
