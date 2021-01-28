package com.jinasoft.study06_ksj.Service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.jinasoft.study06_ksj.R;

public class ServiceMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_main);
    }

    public void onClick(View view) {
        Intent intent = new Intent(ServiceMain.this, ServiceIntentMain.class);
        intent.putExtra(ServiceIntentMain.EXTRA_MESSAGE, getResources().getString(R.string.response)); // 서비스 시작
        startService(intent);
    }
}