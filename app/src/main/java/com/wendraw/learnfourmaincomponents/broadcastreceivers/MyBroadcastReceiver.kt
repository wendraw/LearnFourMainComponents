package com.wendraw.learnfourmaincomponents.broadcastreceivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        Toast.makeText(context, "Received in MyBroadcast", Toast.LENGTH_SHORT).show()
        //拦截广播
        abortBroadcast()
    }
}