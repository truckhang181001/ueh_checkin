package com.codesieucap.ueh_checkin.ui.loginAndRegister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.codesieucap.ueh_checkin.MainActivity;
import com.codesieucap.ueh_checkin.R;
import com.codesieucap.ueh_checkin.WelcomeActivity;

public class LoginActivity extends AppCompatActivity {

    private TextView foget_password;
    private Button login_into_app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_into_app = findViewById(R.id.button_login_into_app);
        foget_password = findViewById(R.id.textview_foget_password);

        String htmlString="<u>Quên mật khẩu</u>";
        foget_password.setText(Html.fromHtml(htmlString));

        login_into_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startMainActiviy();
            }
        });
        foget_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startFogetPassword1Activity();
            }
        });
    }

    private void startFogetPassword1Activity() {
        Intent intent = new Intent(LoginActivity.this, FogetPassword1Activity.class);
        startActivity(intent);
    }

    private void startMainActiviy() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }
}