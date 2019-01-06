package com.tmdtouch.learnfourmaincomponents.activities

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.tmdtouch.learnfourmaincomponents.R
import com.tmdtouch.learnfourmaincomponents.services.MyService
import kotlinx.android.synthetic.main.activity_service.*

class ServiceActivity : AppCompatActivity() {

    private lateinit var mBinder: MyService.MyBinder
    private lateinit var mServiceConnection: ServiceConnection

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)

        start_service_btn.setOnClickListener {
            val intent = Intent(this@ServiceActivity, MyService::class.java)
            startService(intent)
        }

        stop_service_btn.setOnClickListener {
            val intent = Intent(this@ServiceActivity, MyService::class.java)
            stopService(intent)
        }

        bind_service_btn.setOnClickListener {
            mServiceConnection = MyServiceConnection()
            val intent = Intent(this@ServiceActivity, MyService::class.java)
            bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE)
        }

        unbind_service_btn.setOnClickListener {
            unbindService(mServiceConnection)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(mServiceConnection)
    }

    inner class MyServiceConnection : ServiceConnection {

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            Log.d("MyService", "onServiceConnected")
            mBinder = service as MyService.MyBinder
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            Log.d("MyService", "onServiceDisconnected")
        }
    }
}
