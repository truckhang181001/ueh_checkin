package com.codesieucap.ueh_checkin.ui.add_event;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.codesieucap.ueh_checkin.databinding.FragmentAddEventBinding;
import com.codesieucap.ueh_checkin.models.JoinerModel;
import com.codesieucap.ueh_checkin.readGoogleSheet.GetDataTask;
import com.codesieucap.ueh_checkin.readGoogleSheet.InternetConnection;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class AddEventFragment extends Fragment {
    private FragmentAddEventBinding binding;

    private EditText editTextGoogleSheet;
    private Button buttonCreateEvent;

    private List<JoinerModel> listJoiner;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAddEventBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        editTextGoogleSheet = binding.editTextGoogleSheet;
        buttonCreateEvent = binding.buttonCreateEvent;

        buttonCreateEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listJoiner = new ArrayList<>();
                if (InternetConnection.checkConnection(getContext())) {
                    new GetDataTask(listJoiner).execute();
                } else {
                    Snackbar.make(view, "Internet Connection Not Available", Snackbar.LENGTH_LONG).show();
                }
            }
        });


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}