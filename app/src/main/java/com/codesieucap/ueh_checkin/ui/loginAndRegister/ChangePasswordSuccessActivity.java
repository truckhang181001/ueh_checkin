package com.codesieucap.ueh_checkin.ui.loginAndRegister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.codesieucap.ueh_checkin.R;
import com.codesieucap.ueh_checkin.WelcomeActivity;

public class ChangePasswordSuccessActivity extends AppCompatActivity {

    private Button btn_login_now;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password_success);

        btn_login_now = findViewById(R.id.button_login_now);
        btn_login_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startLoginActivity();
            }
        });
    }
    private void startLoginActivity() {
        Intent intent = new Intent(ChangePasswordSuccessActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}