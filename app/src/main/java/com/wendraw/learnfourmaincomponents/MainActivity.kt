package com.wendraw.learnfourmaincomponents

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.wendraw.learnfourmaincomponents.activities.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initButtons()
    }

    /**
     * 创建菜单初始化
     *
     * @param menu
     * @return
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.example_menu, menu)
        Log.d(TAG, "onCreateOptionsMenu")
        return true
    }

    /**
     * 处理 menu 点击事件
     *
     * @param item 当前所点击的 MenuItem
     * @return
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        when (item.itemId) {
            R.id.item1 -> {
                Toast.makeText(this@MainActivity, "Menu item1", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.group_item1 -> {
                Toast.makeText(this@MainActivity, "Menu group_item1", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.group_item2 -> {
                Toast.makeText(this@MainActivity, "Menu group_item2", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.submenu_item1 -> {
                Toast.makeText(this@MainActivity, "Menu submenu_item1", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.group -> {
                Toast.makeText(this@MainActivity, "Menu group", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.submenu -> {
                Toast.makeText(this@MainActivity, "Menu submenu", Toast.LENGTH_SHORT).show()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun initButtons() {
        open_new_activity_btn.setOnClickListener {
            val intent = Intent(this@MainActivity, SecondActivity::class.java)
            startActivity(intent)
        }

        open_alert_dialog_btn.setOnClickListener {
            //通过 AlertDialog.Builder 实例化一个 AlertDialog 对象。
            val builder = AlertDialog.Builder(this@MainActivity)
            //设置 Title 的图标
            builder.setIcon(R.drawable.ic_launcher_foreground)
            //设置 Title 的内容
            builder.setTitle("Warning")
            //设置 Content 来显示一个信息
            builder.setMessage("I'm a fxxc content.")
            //设置一个 PositiveButton
            builder.setPositiveButton("OjbK") { dialog, which -> Toast.makeText(this@MainActivity, "Positive: $which", Toast.LENGTH_SHORT).show() }
            //设置一个 NegativeButton
            builder.setNegativeButton("Cancel") { dialog, which -> Toast.makeText(this@MainActivity, "Negative: $which", Toast.LENGTH_SHORT).show() }
            //设置一个 NeutralButton
            builder.setNeutralButton("Ignore") { dialog, which -> Toast.makeText(this@MainActivity, "Neutral: $which", Toast.LENGTH_SHORT).show() }
            //显示出该对话框
            builder.show()
        }

        learn_launch_mode_btn.setOnClickListener {
            //            Intent intent = new Intent(MainActivity.this, LaunchModeActivity.class);

            val intent = Intent()
            intent.action = "com.wendraw.demo.singleinstance"
            startActivity(intent)
        }

        open_service_activity_btn.setOnClickListener {
            val intent = Intent(this@MainActivity, ServiceActivity::class.java)
            startActivity(intent)
        }

        open_broadcast_activity_btn.setOnClickListener {
            val intent = Intent(this@MainActivity, BroadcastActivity::class.java)
            startActivity(intent)
        }
        open_content_provider_activity_btn.setOnClickListener {
            val intent = Intent(this@MainActivity, ContentProviderActivity::class.java)
            startActivity(intent)
        }
    }
}
