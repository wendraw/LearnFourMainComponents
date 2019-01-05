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
    private Button mLearnLaunchModeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initButtons();
    }

    /**
     * 创建菜单初始化
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        Log.d(TAG, "onCreateOptionsMenu");
        return true;
    }

    /**
     * 处理 menu 点击事件
     *
     * @param item 当前所点击的 MenuItem
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.item1:
                Toast.makeText(MainActivity.this, "Menu item1", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.group_item1:
                Toast.makeText(MainActivity.this, "Menu group_item1", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.group_item2:
                Toast.makeText(MainActivity.this, "Menu group_item2", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.submenu_item1:
                Toast.makeText(MainActivity.this, "Menu submenu_item1", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.group:
                Toast.makeText(MainActivity.this, "Menu group", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.submenu:
                Toast.makeText(MainActivity.this, "Menu submenu", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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

        mLearnLaunchModeBtn = findViewById(R.id.learn_launch_mode_btn);
        mLearnLaunchModeBtn.setOnClickListener(v -> {
//            Intent intent = new Intent(MainActivity.this, LaunchModeActivity.class);

            Intent intent = new Intent();
            intent.setAction("com.wendraw.demo.singleinstance");
            startActivity(intent);
        });
    }
}
