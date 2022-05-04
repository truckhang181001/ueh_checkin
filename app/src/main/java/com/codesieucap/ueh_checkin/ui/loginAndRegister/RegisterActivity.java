package com.codesieucap.ueh_checkin.ui.loginAndRegister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.codesieucap.ueh_checkin.R;

public class RegisterActivity extends AppCompatActivity {

    private TextView tv_agree;
    private Button btn_confirm_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        tv_agree = findViewById(R.id.textview_agree_with_policy);
        btn_confirm_register = findViewById(R.id.button_confirm_register);

        String htmlString="Đồng ý với <b>điều khoản và dịch vụ</b>";
        tv_agree.setText(Html.fromHtml(htmlString));

        tv_agree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPolicyActivity();
            }
        });
        btn_confirm_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startRegisterSuccessActivity();
            }
        });
    }

    private void startPolicyActivity() {
        Intent intent = new Intent(RegisterActivity.this, PolicyActivity.class);
        startActivity(intent);
    }

    private void startRegisterSuccessActivity() {
        Intent intent = new Intent(RegisterActivity.this, RegisterSuccessActivity.class);
        startActivity(intent);
    }
}