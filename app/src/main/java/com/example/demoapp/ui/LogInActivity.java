package com.example.demoapp.ui;

import static com.example.demoapp.utils.Utils.APIKEY;
import static com.example.demoapp.utils.Utils.BASE_URL;
import static com.example.demoapp.utils.Utils.GRANT_TYPE;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.demoapp.R;
import com.example.demoapp.controller.API;
import com.example.demoapp.data.ResponseUser;
import com.example.demoapp.data.User;
import com.example.demoapp.utils.Utils;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LogInActivity extends AppCompatActivity {

    TextInputLayout email, pswrd;
    MaterialButton button;
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        email = findViewById(R.id.logInEmailAddress);
        pswrd = findViewById(R.id.LoginPasswordTextInputLayout);
        button = findViewById(R.id.button_signin);

        //Заполним данными из предыдущего окна
        email.getEditText().setText(Utils.user.getEmail());
        pswrd.getEditText().setText(Utils.user.getPassword());

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API api = retrofit.create(API.class);

        //авторизуем юзера на сервере
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User(String.valueOf(email.getEditText().getText()), String.valueOf(pswrd.getEditText().getText()));
                //запрос к базе данных на авторизацию пользователя
                Call<ResponseUser> call = api.login(GRANT_TYPE, APIKEY, user);
                call.enqueue(new Callback<ResponseUser>() {
                    @Override
                    public void onResponse(Call<ResponseUser> call, Response<ResponseUser> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(LogInActivity.this, "sign in ok", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LogInActivity.this, HomeActivity.class));
                        } else {
                            Toast.makeText(LogInActivity.this, response.code() + "", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseUser> call, Throwable t) {


                        Toast.makeText(LogInActivity.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }

    public void signup(View view) {
        startActivity(new Intent(LogInActivity.this, SignUpActivity.class));
    }

    public void forgot(View view) {
        startActivity(new Intent(LogInActivity.this, ForgotPasswordActivity.class));
    }
}