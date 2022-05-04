package com.codesieucap.ueh_checkin.ui.loginAndRegister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.codesieucap.ueh_checkin.R;

public class PolicyActivity extends AppCompatActivity {

    private Button btn_continue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_policy);

        btn_continue = findViewById(R.id.button_contined_register);
        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBackRegisterActivity();
            }
        });
    }

    private void callBackRegisterActivity() {
        Intent intent = new Intent(PolicyActivity.this, RegisterActivity.class);
        startActivity(intent);
    }
}