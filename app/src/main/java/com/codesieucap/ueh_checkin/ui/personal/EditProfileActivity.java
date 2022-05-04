package com.codesieucap.ueh_checkin.ui.personal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.codesieucap.ueh_checkin.R;

public class EditProfileActivity extends AppCompatActivity {

    private Button btn_confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        btn_confirm = findViewById(R.id.button_confirm_change_profile);
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(EditProfileActivity.this, "Edit  success !", Toast.LENGTH_SHORT).show();
            }
        });
    }
}