package com.codesieucap.ueh_checkin.ui.participant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.codesieucap.ueh_checkin.R;

import java.util.ArrayList;
import java.util.List;

public class ParticipantCheckedinActivity extends AppCompatActivity {

    private RecyclerView rcv_checkedin;
    private ParticipantAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participant_checkedin);

        rcv_checkedin = findViewById(R.id.recycleview_participant_checked_in);
        adapter = new ParticipantAdapter(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcv_checkedin.setLayoutManager(layoutManager);

        adapter.setData(getParticipantList());
        rcv_checkedin.setAdapter(adapter);
    }

    private List<Participant> getParticipantList() {
        List<Participant> list = new ArrayList<>();
        list.add(new Participant("1", "Bui Minh Kha", "ST001", "1"));
        list.add(new Participant("2", "Phan Van Khai", "ST001", "1"));
        list.add(new Participant("3", "Nguyen Truc Khang", "ST001", "1"));
        list.add(new Participant("4", "Ngo Nguyen Duong Khang", "ST001", "1"));
        list.add(new Participant("5", "Bui Minh Kha", "ST001", "1"));
        list.add(new Participant("6", "Phan Van Khai", "ST001", "1"));
        list.add(new Participant("7", "Nguyen Truc Khang", "ST001", "1"));
        list.add(new Participant("8", "Ngo Nguyen Duong Khang", "ST001", "1"));
        return list;
    }
}