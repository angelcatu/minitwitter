package com.tzikin.minitwitter.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tzikin.minitwitter.R;
import com.tzikin.minitwitter.view.common.Constants;
import com.tzikin.minitwitter.view.common.SharedPreferenceManager;
import com.tzikin.minitwitter.view.viewmodel.repository.model.response.LoginSignUpResponse;
import com.tzikin.minitwitter.view.viewmodel.repository.repositories.LoginSignUpRepository;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnRegister;
    private EditText etUsername, etPassword, etEmail;
    private TextView txtLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        bindUIElements();
        btnRegister.setOnClickListener(this);
        txtLogIn.setOnClickListener(this);

    }

    private void bindUIElements() {
        btnRegister = findViewById(R.id.btnRegister);
        etUsername = findViewById(R.id.edtUsernameRegister);
        etEmail = findViewById(R.id.etEmailRegister);
        etPassword = findViewById(R.id.etPasswordRegister);
        txtLogIn = findViewById(R.id.txtLogIn);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnRegister:
                doSignUp();
                break;

            case R.id.txtLogIn:
                goToLogIn();
                break;
        }
    }

    private void doSignUp() {
        String username = etUsername.getText().toString();
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        LoginSignUpRepository repository = new LoginSignUpRepository();
        repository.doSignUpRequest(email, username, password);
        repository.getSingUpResponse().observe(this, response -> {
            if(response != null){
                SharedPreferenceManager.setSomeStringValue(Constants.PREF_TOKEN, response.getToken());
                SharedPreferenceManager.setSomeStringValue(Constants.PREF_USERNAME, response.getUsername());
                SharedPreferenceManager.setSomeStringValue(Constants.PREF_PHOTO_URL, response.getPhotoUrl());
                SharedPreferenceManager.setSomeStringValue(Constants.PREF_CREATED, response.getCreated());
                SharedPreferenceManager.setSomeStringValue(Constants.PREF_ACTIVE, response.getActive());
                startActivity(new Intent(this, Dashboard.class));
                finish();
            }else{
                Toast.makeText(this, getString(R.string.notConnection), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void goToLogIn() {
        startActivity(new Intent(SignUpActivity.this, MainActivity.class));
        finish();
    }
}