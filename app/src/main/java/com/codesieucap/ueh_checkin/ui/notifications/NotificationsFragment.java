package com.codesieucap.ueh_checkin.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.codesieucap.ueh_checkin.ChangePasswordActivity;
import com.codesieucap.ueh_checkin.EditProfileActivity;
import com.codesieucap.ueh_checkin.GuideAndIntroductionActivity;
import com.codesieucap.ueh_checkin.R;
import com.codesieucap.ueh_checkin.SettingActivity;
import com.codesieucap.ueh_checkin.databinding.FragmentNotificationsBinding;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;

    //bmk
    private ImageView edit_info;
    private LinearLayout edit_system, change_password, intro_guideline, log_out;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        edit_system = root.findViewById(R.id.edit_system);
        change_password = root.findViewById(R.id.change_password);
        intro_guideline = root.findViewById(R.id.introduce_and_guideline);
        log_out = root.findViewById(R.id.log_out);
        edit_info = root.findViewById(R.id.edit_info_user);

        edit_system.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSettingActivity();
            }
        });
        change_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startChangePasswordActivity();
            }
        });
        intro_guideline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGuideAndIntroductionActivity();
            }
        });
        log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logOut();
            }
        });
        edit_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startEditProfileActivity();
            }
        });

        return root;
    }

    private void startEditProfileActivity(){
        Intent intent = new Intent(getContext(), EditProfileActivity.class);
        startActivity(intent);
    }
    private void startSettingActivity(){
        Intent intent = new Intent(getContext(), SettingActivity.class);
        startActivity(intent);
    }

    private void startChangePasswordActivity(){
        Intent intent = new Intent(getContext(), ChangePasswordActivity.class);
        startActivity(intent);
    }

    private void startGuideAndIntroductionActivity(){
        Intent intent = new Intent(getContext(), GuideAndIntroductionActivity.class);
        startActivity(intent);
    }
    private void logOut(){
        Toast.makeText(getContext(), "Log out !", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}