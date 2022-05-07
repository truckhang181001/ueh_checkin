package com.codesieucap.ueh_checkin.ui.loginAndRegister;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.codesieucap.ueh_checkin.R;
import com.codesieucap.ueh_checkin.models.UserModel;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;

import java.util.List;
import java.util.UUID;

public class RegisterActivity extends AppCompatActivity {

    private TextView tv_agree;
    private Button btn_confirm_register;
    private FirebaseAuth mAuth;
    private StorageReference mStorageReference;
    private DatabaseReference mDatabase;

    //View items
    private EditText userName,userEmail,userPassword,userPasswordCheck;
    private CheckBox checkBoxPolicy;
    private ImageButton imageButtonAvatar;

    //Variables
    private Uri avatarUri, downloadAvatarUri;

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
        imageButtonAvatar = findViewById(R.id.registerImageButtonAvatar);

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
        imageButtonAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPermissionAvatar();
            }
        });
    }

    private void startPolicyActivity() {
        Intent intent = new Intent(RegisterActivity.this, PolicyActivity.class);
        startActivity(intent);
    }

    private void getPermissionAvatar(){
        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                getAvatarImage();
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Toast.makeText(RegisterActivity.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
            }
        };
        TedPermission.create()
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setPermissions(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                .check();
    }
    private void getAvatarImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK && data != null){
            imageButtonAvatar.setImageURI(data.getData());
            avatarUri = data.getData();
        }
    }

    private void uploadImage(Uri Uri){
        ProgressDialog progressDialog = new ProgressDialog(RegisterActivity.this);
        progressDialog.setIcon(R.drawable.ueh_check_logo);
        progressDialog.setTitle("UEH CHECKIN");
        progressDialog.setMessage("Đang tạo tài khoản...");
        progressDialog.show();


        mStorageReference = FirebaseStorage.getInstance().getReference();

        final String randomKey = UUID.randomUUID().toString();
        StorageReference riversRef = mStorageReference.child("images/"+randomKey);

        UploadTask uploadTask = riversRef.putFile(Uri);
        Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    throw task.getException();
                }

                // Continue with the task to get the download URL
                return riversRef.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                progressDialog.dismiss();
                if (task.isSuccessful()) {
                    downloadAvatarUri = task.getResult();
                    insertFirebaseAccount(mAuth.getCurrentUser().getUid(),userEmail.getText().toString(),userName.getText().toString(),downloadAvatarUri.toString());
                } else {
                    // Handle failures
                    // ...
                }
            }
        });
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
                                //Upload Image and get Link of image to add account into Realtime DB
                                uploadImage(avatarUri);
                                //Intent
                                Intent intent = new Intent(RegisterActivity.this, RegisterSuccessActivity.class);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(RegisterActivity.this, "Lỗi, vui lòng thử lại",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }
        else{
            Toast.makeText(RegisterActivity.this, "Vui lòng kiểm tra lại dữ liệu",Toast.LENGTH_LONG).show();
        }

    }

    private void insertFirebaseAccount(String key, String email, String userName, String imgUri) {
        UserModel user = new UserModel(key,email,userName,imgUri);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("User").child(key).setValue(user);
    }

    private Boolean validateRegister(){
        //Validate du lieu nhap vao
        if(checkBoxPolicy.isChecked() && !userName.getText().toString().equals("") && !userEmail.getText().toString().equals("")
        && !userPassword.getText().toString().equals("") && userPasswordCheck.getText().toString().equals(userPassword.getText().toString())
        && avatarUri != null){
            return true;
        }
        else return false;
    }
}