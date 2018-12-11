package com.tmdtouch.learnfourmaincomponents;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends BaseActivity {

    private Button mOpenActivityBtn;
    private Button mOpenAlertDialogBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initButtons();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        Log.d(TAG, "onCreateOptionsMenu");
        return true;
    }

    private void initButtons() {
        mOpenActivityBtn = findViewById(R.id.open_new_activity_btn);
        mOpenActivityBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        });

        mOpenAlertDialogBtn = findViewById(R.id.open_alert_dialog_btn);
        mOpenAlertDialogBtn.setOnClickListener(v -> {
            //通过 AlertDialog.Builder 实例化一个 AlertDialog 对象。
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            //设置 Title 的图标
            builder.setIcon(R.drawable.ic_launcher_foreground);
            //设置 Title 的内容
            builder.setTitle("Warning");
            //设置 Content 来显示一个信息
            builder.setMessage("I'm a fxxc content.");
            //设置一个 PositiveButton
            builder.setPositiveButton("OjbK", (dialog, which) ->
                    Toast.makeText(MainActivity.this, "Positive: " + which, Toast.LENGTH_SHORT).show());
            //设置一个 NegativeButton
            builder.setNegativeButton("Cancel", (dialog, which) ->
                    Toast.makeText(MainActivity.this, "Negative: " + which, Toast.LENGTH_SHORT).show());
            //设置一个 NeutralButton
            builder.setNeutralButton("Ignore", (dialog, which) ->
                    Toast.makeText(MainActivity.this, "Neutral: " + which, Toast.LENGTH_SHORT).show());
            //显示出该对话框
            builder.show();
        });
    }
}
