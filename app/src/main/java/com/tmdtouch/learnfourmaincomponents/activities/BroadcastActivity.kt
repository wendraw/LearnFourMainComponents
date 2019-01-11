package com.tmdtouch.learnfourmaincomponents.activities

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.v4.content.LocalBroadcastManager
import android.support.v7.app.AppCompatActivity
import com.tmdtouch.learnfourmaincomponents.R
import com.tmdtouch.learnfourmaincomponents.broadcastreceivers.LocalBroadcastReceiver
import kotlinx.android.synthetic.main.activity_broadcast.*

class BroadcastActivity : AppCompatActivity() {

    private lateinit var mIntentFilter: IntentFilter
    private lateinit var mLocalReceiver: LocalBroadcastReceiver
    private lateinit var mLocalBroadcastManager: LocalBroadcastManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broadcast)

        mLocalBroadcastManager = LocalBroadcastManager.getInstance(this)

        //给广播添加过滤器
        mIntentFilter = IntentFilter()
        mIntentFilter.addAction("com.example.broadcasttest.LOCAL_BROADCAST")
        mLocalReceiver = LocalBroadcastReceiver()
        //动态注册本地广播--本地广播无法使用 AndroidManifest.xml 静态注册
        mLocalBroadcastManager.registerReceiver(mLocalReceiver, mIntentFilter)


        send_broadcast_btn.setOnClickListener {
            val intent = Intent("com.example.broadcasttest.MY_BROADCAST")
            //发送普通广播
//            sendBroadcast(intent)
            //发送有序广播
            sendOrderedBroadcast(intent, null)
        }

        send_local_broadcast_btn.setOnClickListener {
            val intent = Intent("com.example.broadcasttest.LOCAL_BROADCAST")
            //发送本地广播
            mLocalBroadcastManager.sendBroadcast(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        //注销本地广播
        mLocalBroadcastManager.unregisterReceiver(mLocalReceiver)
    }
}
