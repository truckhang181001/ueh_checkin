package com.codesieucap.ueh_checkin.ui.loginAndRegister;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.codesieucap.ueh_checkin.R;
import com.codesieucap.ueh_checkin.models.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterActivity extends AppCompatActivity {

    private TextView tv_agree;
    private Button btn_confirm_register;
    private FirebaseAuth mAuth;
    private EditText userName,userEmail,userPassword,userPasswordCheck;
    private CheckBox checkBoxPolicy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        tv_agree = findViewById(R.id.textview_agree_with_policy);
        btn_confirm_register = findViewById(R.id.button_confirm_register);
        userName = findViewById(R.id.registerEditTextUserName);
        userEmail = findViewById(R.id.registerEditTextUserEmail);
        userPassword = findViewById(R.id.registerEditTextUserPassword);
        userPasswordCheck = findViewById(R.id.registerEditTextUserPasswordCheck);
        checkBoxPolicy = findViewById(R.id.registerCheckBoxAgree);

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
        mAuth = FirebaseAuth.getInstance();
        String email = userEmail.getText().toString();
        String password = userPassword.getText().toString();
        if(validateRegister()){
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){

                                Intent intent = new Intent(RegisterActivity.this, RegisterSuccessActivity.class);
                                startActivity(intent);
                            }
                            else {
                                //Hien thi thong bao loi
                            }
                        }
                    });
        }

    }
    private Boolean validateRegister(){
        //Validate du lieu nhap vao
        if(checkBoxPolicy.isChecked() && !userName.getText().toString().equals("") && !userEmail.getText().toString().equals("")
        && !userPassword.getText().toString().equals("") && userPasswordCheck.getText().toString().equals(userPassword.getText().toString())){
            return true;
        }
        else return false;
    }
}