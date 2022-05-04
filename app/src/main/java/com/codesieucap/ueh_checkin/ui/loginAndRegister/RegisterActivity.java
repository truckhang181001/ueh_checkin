package com.codesieucap.ueh_checkin.ui.loginAndRegister;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.codesieucap.ueh_checkin.R;
import com.codesieucap.ueh_checkin.models.UserModel;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private TextView tv_agree;
    private Button btn_confirm_register;
    private DatabaseReference mDatabase;
    private EditText userName,userEmail,userPassword,userPasswordCheck;

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
        mDatabase = FirebaseDatabase.getInstance().getReference();
        String key = mDatabase.child("User").push().getKey();
        UserModel user = new UserModel(key,userEmail.getText().toString(),userPassword.getText().toString(),userName.getText().toString(),"HCM",true,123456,"123456asdfdsf");
        mDatabase.child("User").child(key).setValue(user, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                if(error == null){
                    Intent intent = new Intent(RegisterActivity.this, RegisterSuccessActivity.class);
                    startActivity(intent);
                }
                else{
                    //Hiển thị thông báo lỗi
                }
            }
        });
    }
    private Boolean validateRegister(){
        //true thay thế bằng các validate các editText của user nhập vào
        if(true){
            mDatabase.child("User").addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    if(userEmail.getText().toString().equals(snapshot.getValue(UserModel.class).getEmail())){

                    }
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        return true;
    }
}