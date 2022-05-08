package com.codesieucap.ueh_checkin.ui.personal;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.codesieucap.ueh_checkin.MainActivity;
import com.codesieucap.ueh_checkin.R;
import com.codesieucap.ueh_checkin.databinding.ActivityEditProfileBinding;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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

public class EditProfileActivity extends AppCompatActivity {

    private ActivityEditProfileBinding binding;
    private Button btn_confirm;
    private ImageButton imageButtonAva;
    private EditText editTextName;

    private Uri avatarUri;

    private StorageReference mStorageReference;
    private DatabaseReference mDataReference;
    private FirebaseAuth mAuth;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        btn_confirm = findViewById(R.id.button_confirm_change_profile);
        imageButtonAva = binding.imageButtonUpdateAva;
        editTextName = binding.editTextUpdateName;
        imageButtonAva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPermissionAvatar();
            }
        });
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!editTextName.getText().toString().equals("") && avatarUri==null){
                    updateInfoAcc(editTextName.getText().toString(), null);
                }
                else {
                    uploadImage(avatarUri);
                }
            }
        });
    }
    private void getPermissionAvatar(){
        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                getAvatarImage();
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Toast.makeText(EditProfileActivity.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
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
            imageButtonAva.setImageURI(data.getData());
            avatarUri = data.getData();
        }
    }

    private void uploadImage(Uri Uri){
        ProgressDialog progressDialog = new ProgressDialog(EditProfileActivity.this);
        progressDialog.setIcon(R.drawable.ueh_check_logo);
        progressDialog.setTitle("UEH CHECKIN");
        progressDialog.setMessage("Đang update...");
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
                    Uri downloadAvatarUri = task.getResult();
                    updateInfoAcc(editTextName.getText().toString(),downloadAvatarUri);
                    progressDialog.dismiss();

                } else {
                    // Handle failures
                    // ...
                }
            }
        });
    }

    private void updateInfoAcc(String name, Uri linkAva){
        mDataReference = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        sharedPreferences = getSharedPreferences("dataLogin",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if(!name.equals("")){
            mDataReference.child("User").child(mAuth.getCurrentUser().getUid()).child("userName").setValue(name);
            editor.putString("userName",name);
        }
        if(linkAva != null){
            mDataReference.child("User").child(mAuth.getCurrentUser().getUid()).child("avatarImgUri").setValue(linkAva.toString());
            editor.putString("userAvatar",linkAva.toString());
        }

        editor.commit();

        Toast.makeText(EditProfileActivity.this, "Edit  success !", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(EditProfileActivity.this, MainActivity.class);
        startActivity(intent);
    }


}