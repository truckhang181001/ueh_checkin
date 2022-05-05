package com.codesieucap.ueh_checkin.ui.loginAndRegister;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    private TextView foget_password;
    private EditText userEmail, userPassword;
    private Button login_into_app;
    private SharedPreferences mSharedPreferences;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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
        String email = userEmail.getText().toString();
        String password = userPassword.getText().toString();
        Log.d("TRUCKHANG","TEST");
        mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser user = mAuth.getCurrentUser();

                            mSharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);
                            SharedPreferences.Editor editor = mSharedPreferences.edit();
                            editor.putString("userEmail",user.getEmail());
                            editor.putString("userId",user.getUid());
                            editor.commit();

                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                    }
                });
    }
}