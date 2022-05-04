package com.codesieucap.ueh_checkin.ui.loginAndRegister;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.codesieucap.ueh_checkin.R;

public class FogetPassword2Activity extends AppCompatActivity {

    private TextView send_otp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foget_password2);

        send_otp = findViewById(R.id.send_OTP_again);
        String htmlString="<u>Gửi lại mã</u>";
        send_otp.setText(Html.fromHtml(htmlString));
    }
}