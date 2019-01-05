package com.tmdtouch.learnfourmaincomponents;

import android.content.Intent;
import android.os.Bundle;

public class LaunchModeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_mode);

        findViewById(R.id.restart_activity_btn).setOnClickListener(v -> {
            // 显式调用
//            Intent intent = new Intent(LaunchModeActivity.this, LaunchModeActivity.class);

            // 隐式调用
            Intent intent = new Intent();
            intent.setAction("com.wendraw.demo.singleinstance");
            startActivity(intent);
        });
        findViewById(R.id.open_other_activity_btn).setOnClickListener(v -> {
            Intent intent = new Intent(LaunchModeActivity.this, OtherLaunchModeActivity.class);
            startActivity(intent);
        });

    }
}
