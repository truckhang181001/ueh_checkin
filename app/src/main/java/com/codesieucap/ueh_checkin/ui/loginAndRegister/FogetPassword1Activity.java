package com.codesieucap.ueh_checkin.ui.loginAndRegister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.codesieucap.ueh_checkin.R;

public class FogetPassword1Activity extends AppCompatActivity {

    private Button btn_get_otp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foget_password1);

        btn_get_otp = findViewById(R.id.button_get_otp_code);
        btn_get_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startFogetPassword2Activity();
            }
        });
    }

    private void startFogetPassword2Activity() {
        Intent intent = new Intent(FogetPassword1Activity.this, FogetPassword2Activity.class);
        startActivity(intent);
    }
}