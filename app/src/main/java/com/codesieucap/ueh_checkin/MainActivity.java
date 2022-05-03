package com.codesieucap.ueh_checkin;

import android.os.Bundle;
import android.widget.TableLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.codesieucap.ueh_checkin.databinding.ActivityMainBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

import event.Event;
import event.EventAdapter;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    // bmk
    private RecyclerView recyclerViewEvent;
    private EventAdapter eventAdapter;
    // bmk

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        //bmk
        recyclerViewEvent = findViewById(R.id.recycleview_event);
        eventAdapter = new EventAdapter(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false );
        recyclerViewEvent.setLayoutManager(layoutManager);
        eventAdapter.setData(getListEvent());
        recyclerViewEvent.setAdapter(eventAdapter);
        //bmk
    }

    private List<Event> getListEvent() {
        List<Event> list = new ArrayList<>();
        list.add(new Event(R.drawable.event1, "Lửa xuân", "mô tả", "279, Nguyễn Tri Phương"));
        list.add(new Event(R.drawable.event2, "Nối vòng tay lớn", "mô tả","59C, Nguyễn Đình Chiểu"));
        list.add(new Event(R.drawable.event3, "Sức trẻ kinh tế", "mô tả","Cơ sở N, Bình Chính"));
        list.add(new Event(R.drawable.event1, "Lửa xuân","mô tả", "279, Nguyễn Tri Phương"));
        list.add(new Event(R.drawable.event2, "Nối vòng tay lớn","mô tả", "59C, Nguyễn Đình Chiểu"));
        list.add(new Event(R.drawable.event3, "Sức trẻ kinh tế", "mô tả","Cơ sở N, Bình Chính"));
        return list;
    }

}