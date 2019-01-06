package com.tmdtouch.learnfourmaincomponents.activities;

import android.content.Intent;
import android.os.Bundle;

import com.tmdtouch.learnfourmaincomponents.R;

public class OtherLaunchModeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_single_top);

        findViewById(R.id.open_father_activity_btn).setOnClickListener(v -> {
            Intent intent = new Intent(OtherLaunchModeActivity.this, LaunchModeActivity.class);
            startActivity(intent);
        });
    }
}
