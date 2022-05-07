package com.codesieucap.ueh_checkin.ui.scanQRcode;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.codesieucap.ueh_checkin.R;
import com.codesieucap.ueh_checkin.databinding.ActivityScanningBinding;
import com.codesieucap.ueh_checkin.models.EventModel;
import com.codesieucap.ueh_checkin.models.JoinerModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.Result;

import java.util.Map;
import java.util.Set;

public class ScanningActivity extends AppCompatActivity {

    private ActivityScanningBinding binding;
    private CodeScanner mCodeScanner;
    private EventModel eventItem;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityScanningBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        eventItem = (EventModel) intent.getSerializableExtra("eventData");


        CodeScannerView scannerView = binding.scannerView;
        mCodeScanner = new CodeScanner(this, scannerView);
        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Boolean checkVerify = false;


                        ProgressDialog progressDialog = new ProgressDialog(ScanningActivity.this);
                        progressDialog.setTitle("XÁC THỰC");
                        progressDialog.setMessage("Đang kiểm tra dữ liệu, xin vui lòng đợi...");
                        progressDialog.show();

                        Map<String, JoinerModel> mapJoiner = eventItem.getListJoiner();
                        Set<String> keySet = mapJoiner.keySet();

                        for(String key : keySet){
                            if(mapJoiner.get(key).getTicketCode().equals(result.getText())){
                                checkVerify = true;
                                progressDialog.dismiss();
                                AlertDialog.Builder alertDialog = new AlertDialog.Builder(ScanningActivity.this);
                                alertDialog.setTitle("UEH CHECKIN");
                                alertDialog.setIcon(R.drawable.success_icon);
                                alertDialog.setMessage("Mã định danh: "+mapJoiner.get(key).getIdCode() +
                                        "\nHọ và tên: "+mapJoiner.get(key).getJoinerName());
                                alertDialog.setNeutralButton("Tiếp tục", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        mCodeScanner.startPreview();
                                    }
                                });
                                alertDialog.show();
                                databaseReference = FirebaseDatabase.getInstance().getReference();
                                databaseReference.child("Event").child(eventItem.getIdCode())
                                        .child("listJoiner").child(key).child("status").setValue(JoinerModel.STATUS_CHECKED);
                                break;
                            }
                        }

                        if(!checkVerify){
                            progressDialog.dismiss();
                            AlertDialog.Builder alertDialogB = new AlertDialog.Builder(ScanningActivity.this);
                            alertDialogB.setTitle("UEH CHECKIN");
                            alertDialogB.setIcon(R.drawable.fail_icon);
                            alertDialogB.setMessage("Mã QR không hợp lệ, xin vui lòng thử lại!");
                            alertDialogB.setNeutralButton("Tiếp tục", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    mCodeScanner.startPreview();
                                }
                            });
                            alertDialogB.show();
                        }
                    }
                });
            }
        });
//        scannerView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mCodeScanner.startPreview();
//            }
//        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        mCodeScanner.startPreview();
    }

    @Override
    protected void onPause() {
        mCodeScanner.releaseResources();
        super.onPause();
    }
}