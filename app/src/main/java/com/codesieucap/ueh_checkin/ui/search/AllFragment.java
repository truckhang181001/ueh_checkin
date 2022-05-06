package com.codesieucap.ueh_checkin.ui.search;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codesieucap.ueh_checkin.databinding.FragmentAllBinding;
import com.codesieucap.ueh_checkin.models.EventModel;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import event.SearchEventAdapter;

public class AllFragment extends Fragment {
    private FragmentAllBinding binding;
    private DatabaseReference mDatabase;
    private SharedPreferences mSharedPreferences;

    private List<EventModel> listEvent;

    private RecyclerView rcvEvent;
    private SearchEventAdapter eventAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAllBinding.inflate(inflater,container,false);
        View root = binding.getRoot();

        rcvEvent = binding.recycleviewAll;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rcvEvent.setLayoutManager(layoutManager);

        getResultSearch();
        eventAdapter = new SearchEventAdapter();
        eventAdapter.setData(listEvent);

        rcvEvent.setAdapter(eventAdapter);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void getResultSearch() {
        listEvent = new ArrayList<>();
        mSharedPreferences = getActivity().getSharedPreferences("dataLogin", Context.MODE_PRIVATE);
        String userId = mSharedPreferences.getString("userId","");

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Event").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                EventModel eventItem = snapshot.getValue(EventModel.class);
                if(eventItem.getIdUser().equals(userId)){
                    listEvent.add(eventItem);
                    eventAdapter.notifyDataSetChanged();
                }
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
