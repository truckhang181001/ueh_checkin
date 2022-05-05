package com.codesieucap.ueh_checkin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.codesieucap.ueh_checkin.ui.participant.ParticipantListActivity;

public class EventDetailActivity extends AppCompatActivity {

    private Button btn_manage_participant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        btn_manage_participant = findViewById(R.id.button_manage_list_participant);
        btn_manage_participant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startParticipantListActivity();
            }
        });
    }

    private void startParticipantListActivity() {
        Intent intent = new Intent(EventDetailActivity.this, ParticipantListActivity.class);
        startActivity(intent);
    }
}