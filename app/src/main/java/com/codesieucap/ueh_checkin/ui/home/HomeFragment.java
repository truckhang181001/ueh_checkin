package com.codesieucap.ueh_checkin.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codesieucap.ueh_checkin.R;
import com.codesieucap.ueh_checkin.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

import event.Event;
import event.EventAdapter;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    // bmk
    private RecyclerView recyclerViewEvent;
    private EventAdapter eventAdapter;
    // bmk

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //bmk
        recyclerViewEvent = binding.recycleviewEvent;
        eventAdapter = new EventAdapter(getActivity());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false );
        recyclerViewEvent.setLayoutManager(layoutManager);
        eventAdapter.setData(getListEvent());
        recyclerViewEvent.setAdapter(eventAdapter);
        //bmk

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private List<Event> getListEvent() {
        List<Event> list = new ArrayList<>();
//        list.add(new Event(R.drawable.event1, "Lửa xuân", "279, Nguyễn Tri Phương"));
//        list.add(new Event(R.drawable.event2, "Nối vòng tay lớn", "59C, Nguyễn Đình Chiểu"));
//        list.add(new Event(R.drawable.event3, "Sức trẻ kinh tế", "Cơ sở N, Bình Chính"));
//        list.add(new Event(R.drawable.event1, "Lửa xuân", "279, Nguyễn Tri Phương"));
//        list.add(new Event(R.drawable.event2, "Nối vòng tay lớn", "59C, Nguyễn Đình Chiểu"));
//        list.add(new Event(R.drawable.event3, "Sức trẻ kinh tế", "Cơ sở N, Bình Chính"));
        return list;
    }
}