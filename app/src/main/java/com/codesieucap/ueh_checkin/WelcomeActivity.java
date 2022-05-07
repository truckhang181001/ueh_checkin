package com.codesieucap.ueh_checkin;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.codesieucap.ueh_checkin.ui.loginAndRegister.LoginActivity;
import com.codesieucap.ueh_checkin.ui.loginAndRegister.RegisterActivity;
import com.google.firebase.auth.FirebaseAuth;

public class WelcomeActivity extends AppCompatActivity {

    private Button btn_login, btn_start_now;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        mAuth = FirebaseAuth.getInstance();
        mAuth.signOut();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        btn_login = findViewById(R.id.button_login);
        btn_start_now = findViewById(R.id.button_start_now);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startLoginActivity();
            }
        });
        btn_start_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityRegister();
            }
        });
    }

    private void startActivityRegister() {
        Intent intent = new Intent(WelcomeActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    private void startLoginActivity() {
        Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}