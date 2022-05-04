package com.codesieucap.ueh_checkin.ui.loginAndRegister;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.codesieucap.ueh_checkin.R;

public class LoginActivity extends AppCompatActivity {

    private TextView foget_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        foget_password = findViewById(R.id.foget_password);
        String htmlString="<u>Quên mật khẩu</u>";
        foget_password.setText(Html.fromHtml(htmlString));
    }
}