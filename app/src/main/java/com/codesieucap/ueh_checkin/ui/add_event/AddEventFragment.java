package com.codesieucap.ueh_checkin.ui.add_event;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.codesieucap.ueh_checkin.R;
import com.codesieucap.ueh_checkin.databinding.FragmentAddEventBinding;
import com.codesieucap.ueh_checkin.models.EventModel;
import com.codesieucap.ueh_checkin.models.JoinerModel;
import com.codesieucap.ueh_checkin.readGoogleSheet.GetDataTask;
import com.codesieucap.ueh_checkin.readGoogleSheet.InternetConnection;
import com.codesieucap.ueh_checkin.ui.loginAndRegister.LoginActivity;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class AddEventFragment extends Fragment {
    private FragmentAddEventBinding binding;
    private DatabaseReference mDatabase;
    private StorageReference mStorageReference;
    private SharedPreferences mSharePreferences;
    private FirebaseAuth mAuth;

    private EditText editTextEventName, editTextEventDate, editTextEventTime, editTextEventLocation, editTextEventDetail, editTextGoogleSheet;
    private Button buttonCreateEvent;
    private ImageButton imageButtonEventAvatar, imageButtonEventCover;
    private ImageView imageViewQR;

    private List<JoinerModel> listJoiner;
    private Uri avatarUri,coverUri, cacheUri;
    private EventModel eventAddNew;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAddEventBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mAuth = FirebaseAuth.getInstance();
        mAuth.getCurrentUser().getEmail();
        if(mAuth.getCurrentUser() == null){
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            getActivity().finish();
            startActivity(intent);
        }

        editTextEventName = binding.editTextEventName;
        editTextEventDate = binding.editTextEventDate;
        editTextEventTime = binding.editTextEventTime;
        editTextEventLocation = binding.editTextEventLocation;
        editTextEventDetail = binding.editTextEventDetail;
        imageButtonEventAvatar = binding.imageButtonEventAvatar;
        imageButtonEventCover = binding.imageButtonEventCover;
        editTextGoogleSheet = binding.editTextGoogleSheet;
        buttonCreateEvent = binding.buttonCreateEvent;

        buttonCreateEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listJoiner = new ArrayList<>();
                if (InternetConnection.checkConnection(getContext())) {
                    createEvent();
                } else {
                    Snackbar.make(view, "Internet Connection Not Available", Snackbar.LENGTH_LONG).show();
                }
            }
        });

        imageButtonEventCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPermissionAvatar(1);
            }
        });

        imageButtonEventAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPermissionAvatar(2);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void generateAndSaveQR(){
//        String textQR = "";
//        // Initializing the QR Encoder with your value to be encoded, type you required and Dimension
//        QRGEncoder qrgEncoder = new QRGEncoder(textQR, null, QRGContents.Type.TEXT, 1500);
//        qrgEncoder.setColorBlack(Color.BLACK);
//        qrgEncoder.setColorWhite(Color.WHITE);
//        // Getting QR-Code as Bitmap
//        Bitmap bitmap = qrgEncoder.getBitmap();
//
//        String savePath = Environment.getExternalStorageDirectory().getPath() + "/Pictures/";
//        Uri uriQR = Uri.fromFile(new File(savePath));
//        QRGSaver qrgSaver = new QRGSaver();
//        qrgSaver.save(savePath, textQR, bitmap, QRGContents.ImageType.IMAGE_JPEG);
    }

    private void createEvent(){
        mSharePreferences = getActivity().getSharedPreferences("dataLogin", Context.MODE_PRIVATE);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        listJoiner = new ArrayList<>();
        Map<String,JoinerModel> mapJoiner = new HashMap<String,JoinerModel>();

        String keyEvent = mDatabase.child("Event").push().getKey();
        String keyUser  = mSharePreferences.getString("userId","");
        String eventName = editTextEventName.getText().toString();
        String eventDate = editTextEventDate.getText().toString();
        String eventTime =  editTextEventTime.getText().toString();
        String eventLocation = editTextEventLocation.getText().toString();
        String eventDetail = editTextEventDetail.getText().toString();

        eventAddNew = new EventModel(keyEvent,keyUser,eventName,eventLocation,eventDate,
                eventTime,eventTime,eventDetail,"","",mapJoiner);

        uploadImage(coverUri, "cover");
        uploadImage(avatarUri,"avatar");

        new GetDataTask(getActivity(),mapJoiner,editTextGoogleSheet.getText().toString(), eventAddNew).execute();

        Toast.makeText(getActivity(),"Checking",Toast.LENGTH_LONG).show();
    }

    private void getPermissionAvatar(Integer requestCode){
        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                getAvatarImage(requestCode);
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Toast.makeText(getActivity(), "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
            }
        };
        TedPermission.create()
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setPermissions(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                .check();
    }

    private void getAvatarImage(Integer requestCode){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,requestCode);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK && data != null){
            imageButtonEventCover.setImageURI(data.getData());
            avatarUri = data.getData();
        }
        if(requestCode == 2 && resultCode == RESULT_OK && data != null){
            imageButtonEventAvatar.setImageURI(data.getData());
            coverUri = data.getData();
        }
    }

    private void uploadImage(Uri Uri, String mode){
        ProgressDialog pd = new ProgressDialog(getActivity());
        pd.setTitle("UPLOADING");
        pd.setMessage("Đang đăng tải ảnh...");
        pd.setIcon(R.drawable.ueh_check_logo);
        pd.show();

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
                pd.dismiss();
                if (task.isSuccessful()) {
                    cacheUri = task.getResult();
                    if(mode.equals("cover")){
                        eventAddNew.setCoverImgUri(cacheUri.toString());
                    }
                    if(mode.equals("avatar")){
                        eventAddNew.setAvatarImgUri(cacheUri.toString());
                    }
                } else {
                    Toast.makeText(getActivity(),"Đăng tải hình ảnh thất bại",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
