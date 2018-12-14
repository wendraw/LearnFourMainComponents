package com.tmdtouch.learnfourmaincomponents;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class LaunchModeActivity extends BaseActivity {

    Button mRestartActBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_mode);

        mRestartActBtn = findViewById(R.id.learn_launch_mode_btn);
        mRestartActBtn.setOnClickListener(v -> {
            Intent intent = new Intent(LaunchModeActivity.this, LaunchModeActivity.class);
            startActivity(intent);
        });
    }
}
