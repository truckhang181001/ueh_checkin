package com.codesieucap.ueh_checkin.ui.participant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.codesieucap.ueh_checkin.databinding.ActivityInfoParticipantBinding;
import com.codesieucap.ueh_checkin.models.JoinerModel;

public class InfoParticipantActivity extends AppCompatActivity {

    private ActivityInfoParticipantBinding binding;

    private EditText joinerName, joinerSchoolYear, joinerClass, joinerEmail, joinerID;
    private TextView joinerQR;
    private Button buttonUpdateInfo;

    private JoinerModel joinerItem;
    private String eventID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInfoParticipantBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        joinerItem = (JoinerModel) intent.getSerializableExtra("joinerData");
        eventID = intent.getStringExtra("eventID");

        joinerName = binding.editTextJoinerName;
        joinerSchoolYear = binding.editTextJoinerSchoolYear;
        joinerClass = binding.editTextJoinerClass;
        joinerEmail = binding.editTextJoinerEmail;
        joinerID = binding.editTextJoinerID;
        joinerQR = binding.textViewJoinerQR;
        buttonUpdateInfo = binding.buttonJoinerUpdateInfo;

        joinerName.setText(joinerItem.getJoinerName());
        joinerSchoolYear.setText(joinerItem.getGender());
        joinerClass.setText(joinerItem.getClassName());
        joinerEmail.setText(joinerItem.getEmail());
        joinerID.setText(joinerItem.getIdCode());
        joinerQR.setText(joinerItem.getTicketCodeLink());

        buttonUpdateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}