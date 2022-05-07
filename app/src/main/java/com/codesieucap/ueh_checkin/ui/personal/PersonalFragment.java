package com.codesieucap.ueh_checkin.ui.personal;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.codesieucap.ueh_checkin.R;
import com.codesieucap.ueh_checkin.WelcomeActivity;
import com.codesieucap.ueh_checkin.databinding.FragmentPersonalBinding;
import com.codesieucap.ueh_checkin.ui.loginAndRegister.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

public class PersonalFragment extends Fragment {

    private FragmentPersonalBinding binding;
    private FirebaseAuth mAuth;
    private SharedPreferences mSharePreferences;

    private ImageView edit_profile, avatar;
    private LinearLayout edit_system, change_password, intro_guideline, log_out;
    private TextView tv_dislay_name, tv_display_email;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        PersonalViewModel notificationsViewModel =
                new ViewModelProvider(this).get(PersonalViewModel.class);

        binding = FragmentPersonalBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser() == null){
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            getActivity().finish();
            startActivity(intent);
        }
        

        edit_system = root.findViewById(R.id.edit_system);
        change_password = root.findViewById(R.id.change_password);
        intro_guideline = root.findViewById(R.id.introduce_and_guideline);
        log_out = root.findViewById(R.id.log_out);
        edit_profile = root.findViewById(R.id.edit_info_user);
        tv_dislay_name = root.findViewById(R.id.textview_dislay_name_user);
        tv_display_email = root.findViewById(R.id.textview_email_user);
        avatar = root.findViewById(R.id.imageview_avatar_user);

        getAccount();


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

    private void getAccount(){
        mSharePreferences = getActivity().getSharedPreferences("dataLogin", Context.MODE_PRIVATE);
        String userNamePref = mSharePreferences.getString("userName","");
        String userAvatarPref = mSharePreferences.getString("userAvatar","");
        tv_dislay_name.setText(userNamePref);
        Picasso.get().load(userAvatarPref).into(avatar);
        tv_display_email.setText(mAuth.getCurrentUser().getEmail());
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
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(getContext(), WelcomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}