package com.codesieucap.ueh_checkin.ui.search;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codesieucap.ueh_checkin.R;

import java.util.ArrayList;
import java.util.List;

import event.Event;
import event.SearchEventAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HappenedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HappenedFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HappenedFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HappenedFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HappenedFragment newInstance(String param1, String param2) {
        HappenedFragment fragment = new HappenedFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private RecyclerView rcvEvent;
    private View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_happened, container, false);

        rcvEvent = mView.findViewById(R.id.recycleview_happened);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rcvEvent.setLayoutManager(layoutManager);

        SearchEventAdapter eventAdapter = new SearchEventAdapter();
        eventAdapter.setData(getResultSearch());

        rcvEvent.setAdapter(eventAdapter);
        return mView;
    }

    private List<Event> getResultSearch() {
        List<Event> list = new ArrayList<>();
        list.add(new Event(R.drawable.event1, "Lửa xuân ed", "Rừng núi dang tay lại biển xa. Ta đi vòng tay lớn mãi để nối sơn hà.", "279, Nguyễn Tri Phương"));
        list.add(new Event(R.drawable.event2, "Nối vòng tay lớn ed", "Rừng núi dang tay lại biển xa. Ta đi vòng tay lớn mãi để nối sơn hà.","59C, Nguyễn Đình Chiểu"));
        list.add(new Event(R.drawable.event3, "Sức trẻ kinh tế ed", "Rừng núi dang tay lại biển xa. Ta đi vòng tay lớn mãi để nối sơn hà.","Cơ sở N, Bình Chính"));
        list.add(new Event(R.drawable.event1, "Lửa xuân ed","Rừng núi dang tay lại biển xa. Ta đi vòng tay lớn mãi để nối sơn hà.", "279, Nguyễn Tri Phương"));
        list.add(new Event(R.drawable.event2, "Nối vòng tay lớn ed","Rừng núi dang tay lại biển xa. Ta đi vòng tay lớn mãi để nối sơn hà.mô tả", "59C, Nguyễn Đình Chiểu"));
        list.add(new Event(R.drawable.event3, "Sức trẻ kinh tế ed", "Rừng núi dang tay lại biển xa. Ta đi vòng tay lớn mãi để nối sơn hà.mô tả","Cơ sở N, Bình Chính"));
        return list;
    }
}