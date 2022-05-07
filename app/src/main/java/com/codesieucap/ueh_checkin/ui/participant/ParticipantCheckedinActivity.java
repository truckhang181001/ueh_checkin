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

import java.util.HashMap;
import java.util.Map;

public class ParticipantCheckedinActivity extends AppCompatActivity {
    private ActivityParticipantCheckedinBinding binding;

    private RecyclerView rcv_checkedin;
    private ParticipantAdapter adapter;

    private EventModel eventItem;
    private Map<String, JoinerModel> joinerCheckedList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityParticipantCheckedinBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Get data from Intent
        Intent intent = getIntent();
        eventItem = (EventModel) intent.getSerializableExtra("eventData");
        joinerCheckedList = new HashMap<String, JoinerModel>();

        for(String key : eventItem.getListJoiner().keySet()){
            if(eventItem.getListJoiner().get(key).getStatus().equals(JoinerModel.STATUS_CHECKED)){
                joinerCheckedList.put(key,eventItem.getListJoiner().get(key));
            }
        }

        //Mapping
        rcv_checkedin = findViewById(R.id.recycleview_participant_checked_in);
        adapter = new ParticipantAdapter(this, joinerCheckedList, new ParticipantAdapter.OnClickItemListener() {
            @Override
            public void onLickItemListener(JoinerModel joinerItem) {

            }
        });

        //Setup RecylerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcv_checkedin.setLayoutManager(layoutManager);

//        adapter.setData(joinerCheckedList);
        rcv_checkedin.setAdapter(adapter);
    }
}