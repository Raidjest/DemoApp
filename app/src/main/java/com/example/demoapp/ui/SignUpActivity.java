package com.example.demoapp.ui;

import static com.example.demoapp.utils.Utils.APIKEY;
import static com.example.demoapp.utils.Utils.BASE_URL;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;

import com.example.demoapp.R;
import com.example.demoapp.controller.API;
import com.example.demoapp.data.ResponseUser;
import com.example.demoapp.data.User;
import com.example.demoapp.utils.Utils;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import java.io.File;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUpActivity extends AppCompatActivity {

    AppCompatCheckBox checkBox;
    MaterialButton button;
    TextView signIntext;
    TextInputLayout email, pswrd1, pswrd2;
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        checkBox = findViewById(R.id.signupCheckBox);
        button = findViewById(R.id.button_signup);
        signIntext = findViewById(R.id.textView17);
        email = findViewById(R.id.emailAddress);
        pswrd1 = findViewById(R.id.passwordTextInputLayout);
        pswrd2 = findViewById(R.id.confirmPasswordTextInputLayout);



        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API api = retrofit.create(API.class);


        //по нажатию на кнопку проверим корректность почты, галочку в чекбоксе и совпадение паролей
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String e = String.valueOf(email.getEditText().getText());
                String p1 = String.valueOf(pswrd1.getEditText().getText());
                String p2 = String.valueOf(pswrd2.getEditText().getText());

                //если галочка стоит и проверка корректности почты на шаблон и на то, что все символы маленькие и что пароль 2 раза введен одинаково
                if (checkBox.isChecked()
                        && isEmailValid(e)
                        && p1.equals(p2)
                        && e.equals(e.toLowerCase())) {
                    // запишем данные юзера
                    Utils.user.setEmail(e);
                    Utils.user.setPassword(p1);
                    //создадим юзера на сервере

                    User user = new User(String.valueOf(email.getEditText().getText()), String.valueOf(pswrd1.getEditText().getText()));

                    //запрос к базе данных на создание нового пользователя
                    Call<ResponseUser> call = api.signUpByEmailAndPswrd(APIKEY, user);
                    call.enqueue(new Callback<ResponseUser>() {
                        @Override
                        public void onResponse(Call<ResponseUser> call, Response<ResponseUser> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(SignUpActivity.this, "User create", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignUpActivity.this, HomeActivity.class));
                            } else {
                                Toast.makeText(SignUpActivity.this, response.code() + "", Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseUser> call, Throwable t) {


                            Toast.makeText(SignUpActivity.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });
    }

    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public void signin(View view) {
        //откроем окно LogIn Activity
        startActivity(new Intent(SignUpActivity.this, LogInActivity.class));
    }

}