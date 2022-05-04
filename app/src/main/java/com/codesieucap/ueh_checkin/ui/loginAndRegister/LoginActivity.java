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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.codesieucap.ueh_checkin.MainActivity;
import com.codesieucap.ueh_checkin.R;
import com.codesieucap.ueh_checkin.WelcomeActivity;
import com.codesieucap.ueh_checkin.models.UserModel;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    private TextView foget_password;
    private EditText userEmail, userPassword;
    private Button login_into_app;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        login_into_app = findViewById(R.id.button_login_into_app);
        foget_password = findViewById(R.id.textview_foget_password);
        userEmail = findViewById(R.id.loginEditTextUserEmail);
        userPassword = findViewById(R.id.loginEditTextUserPassword);

        String htmlString="<u>Quên mật khẩu</u>";
        foget_password.setText(Html.fromHtml(htmlString));

        login_into_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginProcess();
            }
        });
        foget_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startFogetPassword1Activity();
            }
        });
    }

    private void startFogetPassword1Activity() {
        Intent intent = new Intent(LoginActivity.this, FogetPassword1Activity.class);
        startActivity(intent);
    }

    private void loginProcess() {
        mDatabase.child("User").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                UserModel user = snapshot.getValue(UserModel.class);
                if(userEmail.getText().toString().equals(user.getEmail()) && userPassword.getText().toString().equals(user.getPassword())){
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                }
                else{
                    // Trường hợp đăng nhập bị lỗi
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
}