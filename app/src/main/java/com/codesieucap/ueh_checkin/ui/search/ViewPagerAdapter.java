package com.codesieucap.ueh_checkin.ui.search;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull SearchFragment fragmentActivity) {
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
