package com.codesieucap.ueh_checkin.ui.participant;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codesieucap.ueh_checkin.R;
import com.codesieucap.ueh_checkin.databinding.ActivityParticipantCheckedinBinding;
import com.codesieucap.ueh_checkin.models.EventModel;
import com.codesieucap.ueh_checkin.models.JoinerModel;

import java.util.ArrayList;
import java.util.List;

public class ParticipantCheckedinActivity extends AppCompatActivity {
    private ActivityParticipantCheckedinBinding binding;

    private RecyclerView rcv_checkedin;
    private ParticipantAdapter adapter;

    private EventModel eventItem;
    private List<JoinerModel> joinerCheckedList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityParticipantCheckedinBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Get data from Intent
        Intent intent = getIntent();
        eventItem = (EventModel) intent.getSerializableExtra("eventData");
        joinerCheckedList = new ArrayList<>();
        for(JoinerModel item : eventItem.getListJoiner()){
            if(item.getStatus() == JoinerModel.STATUS_CHECKED){
                joinerCheckedList.add(item);
            }
        }

        //Mapping
        rcv_checkedin = findViewById(R.id.recycleview_participant_checked_in);
        adapter = new ParticipantAdapter(this);

        //Setup RecylerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcv_checkedin.setLayoutManager(layoutManager);

        adapter.setData(joinerCheckedList);
        rcv_checkedin.setAdapter(adapter);
    }
}