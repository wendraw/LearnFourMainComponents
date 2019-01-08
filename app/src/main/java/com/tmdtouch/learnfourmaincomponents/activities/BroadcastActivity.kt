package com.tmdtouch.learnfourmaincomponents.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.tmdtouch.learnfourmaincomponents.R
import kotlinx.android.synthetic.main.activity_broadcast.*

class BroadcastActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broadcast)

        send_broadcast_btn.setOnClickListener {
            val intent = Intent("com.example.broadcasttest.MY_BROADCAST")
            sendBroadcast(intent)
        }
    }
}
