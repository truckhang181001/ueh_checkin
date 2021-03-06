package com.codesieucap.ueh_checkin.ui.loginAndRegister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.codesieucap.ueh_checkin.R;

public class RegisterSuccessActivity extends AppCompatActivity {

    private Button btn_login_after_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_success);

        btn_login_after_register = findViewById(R.id.button_login_now_after_register);
        btn_login_after_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startLoginActivity();
            }
        });
    }

    private void startLoginActivity() {
        Intent intent = new Intent(RegisterSuccessActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}