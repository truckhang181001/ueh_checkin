package com.codesieucap.ueh_checkin.ui.home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codesieucap.ueh_checkin.EventDetailActivity;
import com.codesieucap.ueh_checkin.databinding.FragmentHomeBinding;
import com.codesieucap.ueh_checkin.models.EventModel;
import com.codesieucap.ueh_checkin.ui.loginAndRegister.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import event.EventAdapter;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    // bmk
    private RecyclerView recyclerViewEvent;
    private EventAdapter eventAdapter;
    // bmk

    //Data
    private DatabaseReference mDatabase;
    private SharedPreferences mSharePreferences;
    private FirebaseAuth mAuth;

    //View items
    private TextView textViewUserName;
    private ImageView imageViewUserAvatar;


    private List<EventModel> listOfEvent;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //Mapping
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        textViewUserName = binding.textviewUserName;
        imageViewUserAvatar = binding.avatarUser;

        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser() == null){
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            getActivity().finish();
            startActivity(intent);
        }

        getAccount();

        //bmk
        getDataEvent();
        recyclerViewEvent = binding.recycleviewEvent;
        eventAdapter = new EventAdapter(getActivity(), listOfEvent, new EventAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(EventModel item) {
                Intent intent = new Intent(getActivity(), EventDetailActivity.class);
                intent.putExtra("eventData",item);
                startActivity(intent);

            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false );
        recyclerViewEvent.setLayoutManager(layoutManager);
        recyclerViewEvent.setAdapter(eventAdapter);
        //bmk

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void getAccount(){
        mSharePreferences = getActivity().getSharedPreferences("dataLogin", Context.MODE_PRIVATE);
        String userNamePref = mSharePreferences.getString("userName","");
        String userAvatarPref = mSharePreferences.getString("userAvatar","");
        textViewUserName.setText("Hello, " + userNamePref);
        Picasso.get().load(userAvatarPref).into(imageViewUserAvatar);
    }

    private void getDataEvent(){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        listOfEvent = new ArrayList<>();
        mDatabase.child("Event").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                listOfEvent.add(snapshot.getValue(EventModel.class));
                eventAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}