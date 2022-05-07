package com.codesieucap.ueh_checkin.ui.loginAndRegister;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.codesieucap.ueh_checkin.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class FogetPassword1Activity extends AppCompatActivity {

    private Button btn_reset_password;
    public EditText edt_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foget_password1);

        btn_reset_password = findViewById(R.id.button_reset_password);
        edt_email = findViewById(R.id.editText_email);

        btn_reset_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startFogetPassword2Activity();
            }
        });
    }

    private void startFogetPassword2Activity() {

        FirebaseAuth auth = FirebaseAuth.getInstance();
        String emailAddress = edt_email.getText().toString();

        auth.sendPasswordResetEmail(emailAddress)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(FogetPassword1Activity.this, "Đã gửi email", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        Intent intent = new Intent(FogetPassword1Activity.this, FogetPassword2Activity.class);
        startActivity(intent);
    }
}