package com.tmdtouch.learnfourmaincomponents.services

import android.app.Service
import android.content.Intent
import android.content.ServiceConnection
import android.os.Binder
import android.os.IBinder
import android.util.Log

class MyService : Service() {
    private val TAG = "Life Cycle - " + javaClass.simpleName

    override fun onCreate() {
        Log.d(TAG, "**************onCreate**************")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.d(TAG, "onBind")
        return MyBinder()
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.d(TAG, "onUnbind")
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy")
    }

    interface MyIBinder {
        fun invokeMethodInMyService()
    }

    inner class MyBinder : Binder(), MyIBinder {

        fun stopService(serviceConnection: ServiceConnection) {
            unbindService(serviceConnection)
        }

        override fun invokeMethodInMyService() {
            for (i in 0..19) {
                println("service is opening")
            }
        }

    }
}