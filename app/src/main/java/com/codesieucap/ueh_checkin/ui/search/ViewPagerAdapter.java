package com.codesieucap.ueh_checkin.ui.search;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.codesieucap.ueh_checkin.ui.dashboard.DashboardFragment;
import com.codesieucap.ueh_checkin.ui.search.AllFragment;
import com.codesieucap.ueh_checkin.ui.search.HappenedFragment;
import com.codesieucap.ueh_checkin.ui.search.HappeningFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull DashboardFragment fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0: return new HappeningFragment();
            case 1: return new HappenedFragment();
            case 2: return new AllFragment();
            default: return new HappeningFragment();
            }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
