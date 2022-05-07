package com.codesieucap.ueh_checkin.ui.personal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.codesieucap.ueh_checkin.R;
import com.codesieucap.ueh_checkin.ui.loginAndRegister.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangePasswordActivity extends AppCompatActivity {

    private Button btn_confirm;
    private EditText edt_newpass, edt_newpass_retype;
    public ProgressDialog progressDialog;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        btn_confirm = findViewById(R.id.button_confirm_edit_password);
        edt_newpass = findViewById(R.id.edittext_newpassword);
        edt_newpass_retype = findViewById(R.id.edittext_newpassword_retype);
        progressDialog = new ProgressDialog(this);

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                String newpass = edt_newpass.getText().toString().trim();
                String newpass_retype = edt_newpass_retype.getText().toString();

                changePassword(newpass);
            }
        });
    }
    private void changePassword(String newPassword){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        user.updatePassword(newPassword)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ChangePasswordActivity.this, "Thay đổi mật khẩu thành công !", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            mAuth = FirebaseAuth.getInstance();
                            mAuth.signOut();
                            Intent intent = new Intent(ChangePasswordActivity.this, LoginActivity.class);
                            finish();
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(ChangePasswordActivity.this, "Thay đổi mật khẩu thất bại !", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    }
                });

    }
}