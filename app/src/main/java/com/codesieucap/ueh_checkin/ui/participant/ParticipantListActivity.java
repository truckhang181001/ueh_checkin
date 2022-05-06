package com.codesieucap.ueh_checkin.ui.participant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codesieucap.ueh_checkin.R;
import com.codesieucap.ueh_checkin.databinding.ActivityParticipantListBinding;
import com.codesieucap.ueh_checkin.models.EventModel;

public class ParticipantListActivity extends AppCompatActivity {

    private ActivityParticipantListBinding binding;

    private EventModel eventItem;

    private RecyclerView rcv_participant;
    private ParticipantAdapter adapter;
    private Button btn_add_list, btn_add_personal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding =ActivityParticipantListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Get data from Intent
        Intent intent = getIntent();
        eventItem = (EventModel) intent.getSerializableExtra("eventData");

        //Mapping
        rcv_participant = findViewById(R.id.recycleview_participant);
        adapter = new ParticipantAdapter(this);
        btn_add_list = findViewById(R.id.button_add_list_participant);
        btn_add_personal = findViewById(R.id.button_add_one_participant);

        //Fill data Listview
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcv_participant.setLayoutManager(layoutManager);

        adapter.setData(eventItem.getListJoiner());
        rcv_participant.setAdapter(adapter);

        btn_add_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAddParticipantListActivity();
            }
        });
        btn_add_personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAddParticipantActivity();
            }
        });
    }

    private void startAddParticipantListActivity() {
        Intent intent = new Intent(ParticipantListActivity.this, AddParticipantListActivity.class);
        startActivity(intent);
    }
    private void startAddParticipantActivity() {
        Intent intent = new Intent(ParticipantListActivity.this, AddParticipantActivity.class);
        startActivity(intent);
    }
}