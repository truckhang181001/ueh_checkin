package com.codesieucap.ueh_checkin.ui.participant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.codesieucap.ueh_checkin.R;

import java.util.ArrayList;
import java.util.List;

public class ParticipantListActivity extends AppCompatActivity {

    private RecyclerView rcv_participant;
    private ParticipantAdapter adapter;
    private Button btn_add_list, btn_add_personal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participant_list);

        rcv_participant = findViewById(R.id.recycleview_participant);
        adapter = new ParticipantAdapter(this);
        btn_add_list = findViewById(R.id.button_add_list_participant);
        btn_add_personal = findViewById(R.id.button_add_one_participant);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcv_participant.setLayoutManager(layoutManager);

        adapter.setData(getParticipantList());
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

    private List<Participant> getParticipantList() {
        List<Participant> list = new ArrayList<>();
        list.add(new Participant("1", "Bui Minh Kha", "ST001", "1"));
        list.add(new Participant("2", "Phan Van Khai", "ST001", "1"));
        list.add(new Participant("3", "Nguyen Truc Khang", "ST001", "0"));
        list.add(new Participant("4", "Ngo Nguyen Duong Khang", "ST001", "0"));
        list.add(new Participant("5", "Bui Minh Kha", "ST001", "0"));
        list.add(new Participant("6", "Phan Van Khai", "ST001", "0"));
        list.add(new Participant("7", "Nguyen Truc Khang", "ST001", "1"));
        list.add(new Participant("8", "Ngo Nguyen Duong Khang", "ST001", "1"));
        return list;
    }
}