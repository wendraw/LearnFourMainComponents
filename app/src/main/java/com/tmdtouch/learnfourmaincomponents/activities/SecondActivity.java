package com.tmdtouch.learnfourmaincomponents.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.tmdtouch.learnfourmaincomponents.fragments.FirstFragment;
import com.tmdtouch.learnfourmaincomponents.R;

public class SecondActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_layout, new FirstFragment());
        transaction.commit();
    }
}
