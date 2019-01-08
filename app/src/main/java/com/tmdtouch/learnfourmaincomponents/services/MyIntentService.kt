package com.tmdtouch.learnfourmaincomponents.services

import android.app.IntentService
import android.content.Intent
import android.util.Log

class MyIntentService : IntentService("MyIntentService") {

    override fun onHandleIntent(intent: Intent?) {
        //打印当前线程 id
        Log.d("MyIntentService", "This id is " + Thread.currentThread().id)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MyIntentService", "onDestroy executed.")
    }
}