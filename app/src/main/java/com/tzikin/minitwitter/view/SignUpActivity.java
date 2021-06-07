package com.tzikin.minitwitter.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.tzikin.minitwitter.R;

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
                break;

            case R.id.txtLogIn:
                goToLogIn();
                break;
        }
    }

    private void goToLogIn() {
        startActivity(new Intent(SignUpActivity.this, MainActivity.class));
        finish();
    }
}