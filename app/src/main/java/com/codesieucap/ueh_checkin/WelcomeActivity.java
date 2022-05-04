package com.codesieucap.ueh_checkin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.codesieucap.ueh_checkin.R;
import com.codesieucap.ueh_checkin.ui.loginAndRegister.LoginActivity;
import com.codesieucap.ueh_checkin.ui.loginAndRegister.RegisterActivity;

public class WelcomeActivity extends AppCompatActivity {

    private Button btn_login, btn_start_now;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

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