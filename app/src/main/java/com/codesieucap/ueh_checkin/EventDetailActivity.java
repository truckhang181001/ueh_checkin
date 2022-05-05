package com.codesieucap.ueh_checkin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.codesieucap.ueh_checkin.ui.participant.ParticipantCheckedinActivity;
import com.codesieucap.ueh_checkin.ui.participant.ParticipantListActivity;

public class EventDetailActivity extends AppCompatActivity {

    private Button btn_manage_participant, btn_list_checked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        btn_manage_participant = findViewById(R.id.button_manage_list_participant);
        btn_list_checked = findViewById(R.id.list_participant_checked_in);

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
    }

    private void startPartivityCheckedinActivity() {
        Intent intent = new Intent(EventDetailActivity.this, ParticipantCheckedinActivity.class);
        startActivity(intent);
    }

    private void startParticipantListActivity() {
        Intent intent = new Intent(EventDetailActivity.this, ParticipantListActivity.class);
        startActivity(intent);
    }
}