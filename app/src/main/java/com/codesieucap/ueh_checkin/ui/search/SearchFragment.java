package com.codesieucap.ueh_checkin.ui.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.codesieucap.ueh_checkin.R;
import com.codesieucap.ueh_checkin.databinding.FragmentSearchBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class SearchFragment extends Fragment {

    private FragmentSearchBinding binding;

    //bmk
    private TabLayout mTabLayout;
    private ViewPager2 mViewPager;
    //bmk


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SearchViewModel dashboardViewModel =
                new ViewModelProvider(this).get(SearchViewModel.class);

        binding = FragmentSearchBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //bmk
        mTabLayout = root.findViewById(R.id.tab_layout);
        mViewPager = root.findViewById(R.id.search_view_pager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(SearchFragment.this);
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