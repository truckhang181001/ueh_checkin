package com.codesieucap.ueh_checkin.ui.loginAndRegister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.codesieucap.ueh_checkin.R;

public class FogetPassword2Activity extends AppCompatActivity {

    private Button btn_confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foget_password2);

        btn_confirm = findViewById(R.id.button_i_understood);

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startFogetPassword3Activity();
            }
        });
    }

    private void startFogetPassword3Activity() {
        Intent intent = new Intent(FogetPassword2Activity.this, LoginActivity.class);
        startActivity(intent);
    }
}