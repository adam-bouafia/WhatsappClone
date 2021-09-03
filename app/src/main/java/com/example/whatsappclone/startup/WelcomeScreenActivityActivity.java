package com.example.whatsappclone.startup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.whatsappclone.MainActivity;
import com.example.whatsappclone.R;
import com.example.whatsappclone.auth.PhoneLoginActivity;

public class WelcomeScreenActivityActivity extends AppCompatActivity {

    AppCompatButton btnAgree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen_activity);

        btnAgree=findViewById(R.id.btn_agree);

        btnAgree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeScreenActivityActivity.this, PhoneLoginActivity.class));
            }
        });
    }
}