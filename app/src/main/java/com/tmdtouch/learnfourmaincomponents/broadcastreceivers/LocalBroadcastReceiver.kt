package com.tmdtouch.learnfourmaincomponents.broadcastreceivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class LocalBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Toast.makeText(context, "Received in LocalBroadcast", Toast.LENGTH_SHORT).show()
    }
}