package com.example.demoapp.ui;

import static com.example.demoapp.utils.Utils.APIKEY;
import static com.example.demoapp.utils.Utils.BASE_URL;
import static com.example.demoapp.utils.Utils.GRANT_TYPE;
import static com.example.demoapp.utils.Utils.TOKEN;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;


import com.example.demoapp.R;
import com.example.demoapp.controller.API;
import com.example.demoapp.data.ResponseUser;
import com.example.demoapp.data.User;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewPasswordActivity extends AppCompatActivity {

    Retrofit retrofit;
    MaterialButton newPassButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password);
        newPassButton = findViewById(R.id.button_NewPassSignIn);

        String newToken = getIntent().getStringExtra("newToken");
        String EmailForPassChange = getIntent().getStringExtra("passedEmail");

        TextInputEditText newPassConfirm = findViewById(R.id.newPasswordConfirmField);
        TextInputEditText newPassField = findViewById(R.id.newPasswordField);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API api = retrofit.create(API.class);

        newPassButton.setOnClickListener(new View.OnClickListener() {
            //проверка соответствия двух введенных паролей
            @Override
            public void onClick(View v) {
                if (newPassConfirm.getEditableText().toString() != newPassField.getEditableText().toString()) {
                    Toast.makeText(NewPasswordActivity.this, "Password`s doesn`t match", Toast.LENGTH_SHORT).show();
                } else {
                    //отправка запроса на базу данных для смены пароля
                    User user = new User(EmailForPassChange, newPassConfirm.getEditableText().toString());
                    Call<Void> call = api.updatePassword(APIKEY, "Bearer " + newToken, user);
                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(NewPasswordActivity.this, "Password changed", Toast.LENGTH_SHORT).show();
                                //отправка запроса на авторизацию с новым паролем
                                Call<ResponseUser> logInCall = api.login(GRANT_TYPE, APIKEY, user);
                                logInCall.enqueue(new Callback<ResponseUser>() {
                                    @Override
                                    public void onResponse(Call<ResponseUser> call, Response<ResponseUser> response) {
                                        if (response.isSuccessful()) {

                                            startActivity(new Intent(NewPasswordActivity.this, HomeActivity.class));
                                        } else {
                                            Toast.makeText(NewPasswordActivity.this, response.code() + "", Toast.LENGTH_SHORT).show();

                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ResponseUser> call, Throwable t) {


                                        Toast.makeText(NewPasswordActivity.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable throwable) {
                            Toast.makeText(NewPasswordActivity.this, throwable.getMessage().toString(), Toast.LENGTH_SHORT).show();

                        }
                    });

                }
            }
        });





    }
}