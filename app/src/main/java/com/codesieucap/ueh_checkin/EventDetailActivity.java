package com.codesieucap.ueh_checkin;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.codesieucap.ueh_checkin.databinding.ActivityEventDetailBinding;
import com.codesieucap.ueh_checkin.models.EventModel;
import com.codesieucap.ueh_checkin.ui.participant.ParticipantCheckedinActivity;
import com.codesieucap.ueh_checkin.ui.participant.ParticipantListActivity;
import com.codesieucap.ueh_checkin.ui.scanQRcode.ScanningActivity;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;
import com.squareup.picasso.Picasso;

import java.util.List;

public class EventDetailActivity extends AppCompatActivity {

    private Button btn_manage_participant, btn_list_checked,btnCheckin;
    private TextView eventName, eventAddress, eventDetail, eventDate, eventTime;
    private ImageView eventCover;

    private EventModel eventItem;

    private ActivityEventDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityEventDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Get Data Event Item
        Intent intent = getIntent();
        eventItem = (EventModel) intent.getSerializableExtra("eventData");

        //Mapping
        btn_manage_participant = findViewById(R.id.button_manage_list_participant);
        btn_list_checked = findViewById(R.id.list_participant_checked_in);
        eventName = binding.textViewEventName;
        eventAddress = binding.textViewEventAddress;
        eventDetail = binding.textViewEventDetail;
        eventDate = binding.textViewEventDate;
        eventTime = binding.textViewEventTime;
        eventCover = binding.imageViewEventCover;
        btnCheckin = binding.buttonEventCheckin;

        //Fill Data into items
        eventDate.setText(eventItem.getDate());
        eventTime.setText(eventItem.getStartTime());
        eventName.setText(eventItem.getEventName());
        eventAddress.setText(eventItem.getAddress());
        eventDetail.setText(eventItem.getDetail());
        Picasso.get().load(eventItem.getCoverImgUri()).into(eventCover);

        btn_manage_participant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startParticipantListActivity();
            }
        });

        btn_list_checked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPartivityCheckedinActivity();
            }
        });

        btnCheckin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPermissionCamera();
            }
        });
    }

    private void startPartivityCheckedinActivity() {
        Intent intent = new Intent(EventDetailActivity.this, ParticipantCheckedinActivity.class);
        intent.putExtra("eventData",eventItem);
        startActivity(intent);
    }

    private void startParticipantListActivity() {
        Intent intent = new Intent(EventDetailActivity.this, ParticipantListActivity.class);
        intent.putExtra("eventData",eventItem);
        startActivity(intent);
    }

    private void getPermissionCamera(){
        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                Intent intent = new Intent(EventDetailActivity.this, ScanningActivity.class);
                intent.putExtra("eventData",eventItem);
                startActivity(intent);
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Toast.makeText(EventDetailActivity.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
            }
        };
        TedPermission.create()
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setPermissions(Manifest.permission.CAMERA)
                .check();
    }
}