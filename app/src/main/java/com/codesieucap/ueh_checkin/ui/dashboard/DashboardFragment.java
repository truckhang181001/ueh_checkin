package com.codesieucap.ueh_checkin.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.codesieucap.ueh_checkin.R;
import com.codesieucap.ueh_checkin.databinding.FragmentDashboardBinding;
import com.codesieucap.ueh_checkin.ui.search.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

import event.Event;
import event.SearchEventAdapter;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

    //bmk
    private TabLayout mTabLayout;
    private ViewPager2 mViewPager;
    //bmk


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //bmk
        mTabLayout = root.findViewById(R.id.tab_layout);
        mViewPager = root.findViewById(R.id.search_view_pager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(DashboardFragment.this);
        mViewPager.setAdapter(viewPagerAdapter);
        new TabLayoutMediator(mTabLayout, mViewPager, (tab, position) -> {
            switch (position){
                case 0: tab.setText("Đang diễn ra"); break;
                case 1: tab.setText("Đã diễn ra"); break;
                case 2: tab.setText("Tất cả"); break;
            }
        }).attach();
        //bmk

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}