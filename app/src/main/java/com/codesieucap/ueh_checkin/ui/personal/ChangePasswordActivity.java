package com.codesieucap.ueh_checkin.ui.personal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.codesieucap.ueh_checkin.R;

public class ChangePasswordActivity extends AppCompatActivity {

    private Button btn_confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        btn_confirm = findViewById(R.id.button_confirm_edit_password);
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ChangePasswordActivity.this, "Change password success !", Toast.LENGTH_SHORT).show();
            }
        });
    }
}