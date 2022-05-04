package com.codesieucap.ueh_checkin.ui.loginAndRegister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.codesieucap.ueh_checkin.R;

public class FogetPassword3Activity extends AppCompatActivity {

    private Button btn_confirm_new_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foget_password3);

        btn_confirm_new_pass = findViewById(R.id.button_confirm_new_password);
        btn_confirm_new_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startChangePasswordSuccessActivity();
            }
        });
    }

    private void startChangePasswordSuccessActivity() {
        Intent intent = new Intent(FogetPassword3Activity.this, ChangePasswordSuccessActivity.class);
        startActivity(intent);
    }
}