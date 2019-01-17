package com.wendraw.learnfourmaincomponents.activities

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.wendraw.learnfourmaincomponents.R
import com.wendraw.learnfourmaincomponents.services.MyIntentService
import com.wendraw.learnfourmaincomponents.services.MyService
import kotlinx.android.synthetic.main.activity_service.*

class ServiceActivity : AppCompatActivity() {

    private lateinit var mBinder: MyService.MyBinder
    private lateinit var mServiceConnection: ServiceConnection

    private var isBind = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)

        //使用 startService 方式启动 Service
        start_service_btn.setOnClickListener {
            val intent = Intent(this@ServiceActivity, MyService::class.java)
            startService(intent)
        }

        //停止 Service
        stop_service_btn.setOnClickListener {
            val intent = Intent(this@ServiceActivity, MyService::class.java)
            stopService(intent)
        }

        //使用 bindService  方式启动 Service
        bind_service_btn.setOnClickListener {
            isBind = true
            mServiceConnection = MyServiceConnection()
            val intent = Intent(this@ServiceActivity, MyService::class.java)
            bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE)
        }

        //解绑 Service
        unbind_service_btn.setOnClickListener {
            unbindService(mServiceConnection)
        }

        //使用 startService 方式启动 IntentService
        start_intent_service_btn.setOnClickListener {
            //打印主线程的 id
            Log.d("ServiceActivity", "Thread id is " + Thread.currentThread().id)
            val intent = Intent(this@ServiceActivity, MyIntentService::class.java)
            startService(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isBind) {
            //当活动被销毁时，需要解绑 Service
            unbindService(mServiceConnection)
        }
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
