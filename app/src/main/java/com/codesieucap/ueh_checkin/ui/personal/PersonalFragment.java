package com.codesieucap.ueh_checkin.ui.personal;


import android.content.Intent;
import android.net.Uri;
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

import com.codesieucap.ueh_checkin.EventDetailActivity;
import com.codesieucap.ueh_checkin.R;
import com.codesieucap.ueh_checkin.WelcomeActivity;
import com.codesieucap.ueh_checkin.databinding.FragmentPersonalBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class PersonalFragment extends Fragment {

    private FragmentPersonalBinding binding;

    private ImageView edit_profile, avatar;
    private LinearLayout edit_system, change_password, intro_guideline, log_out;
    private TextView tv_dislay_name, tv_display_email;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        PersonalViewModel notificationsViewModel =
                new ViewModelProvider(this).get(PersonalViewModel.class);

        binding = FragmentPersonalBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        edit_system = root.findViewById(R.id.edit_system);
        change_password = root.findViewById(R.id.change_password);
        intro_guideline = root.findViewById(R.id.introduce_and_guideline);
        log_out = root.findViewById(R.id.log_out);
        edit_profile = root.findViewById(R.id.edit_info_user);
        tv_dislay_name = root.findViewById(R.id.textview_dislay_name_user);
        tv_display_email = root.findViewById(R.id.textview_email_user);
        avatar = root.findViewById(R.id.imageview_avatar_user);

//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        tv_dislay_name.setText(user.getDisplayName());
//        tv_display_email.setText(user.getEmail());

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
        edit_profile.setOnClickListener(new View.OnClickListener() {
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
        Intent intent = new Intent(getContext(), WelcomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}