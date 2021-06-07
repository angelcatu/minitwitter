package com.tzikin.minitwitter.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tzikin.minitwitter.R;
import com.tzikin.minitwitter.view.viewmodel.repository.model.response.LoginSignUpResponse;
import com.tzikin.minitwitter.view.viewmodel.repository.repositories.LoginSignUpRepository;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnLogin;
    private TextView txtGoSignUp;
    private EditText edtEmail, edtPassword;

    private LoginSignUpRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindUIElements();
        btnLogin.setOnClickListener(this);
        txtGoSignUp.setOnClickListener(this);
    }

    private void bindUIElements() {
        btnLogin = findViewById(R.id.btnLogin);
        txtGoSignUp = findViewById(R.id.txtSignUp);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.etPassword);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnLogin:
                doLogIn();
                break;

            case R.id.txtSignUp:
                goToSignUp();
                break;
        }
    }

    private void doLogIn() {

        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();

        repository = new LoginSignUpRepository();
        repository.doLoginRequest(email, password);
        repository.getLoginSignUpResponse().observe(this, response -> {
            if(response != null){
                startActivity(new Intent(this, Dashboard.class));
                finish();
            }else{
                Toast.makeText(this, getString(R.string.notConnection), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void goToSignUp() {
        startActivity(new Intent(MainActivity.this, SignUpActivity.class));
        finish();
    }

}