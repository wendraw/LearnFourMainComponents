package com.wendraw.learnfourmaincomponents.activities

import android.Manifest
import android.content.pm.PackageManager
import android.database.Cursor
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.widget.ArrayAdapter
import android.widget.Toast
import com.wendraw.learnfourmaincomponents.R
import kotlinx.android.synthetic.main.activity_content_provider.*
import java.util.ArrayList

class ContentProviderActivity : AppCompatActivity() {

    private lateinit var mAdapter: ArrayAdapter<String>
    private val mContactsList: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content_provider)

        mAdapter = ArrayAdapter(this, R.layout.simple_list_item, mContactsList)
        content_provider_list_view.adapter = mAdapter

        //检查权限
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.READ_CONTACTS), 1)
        } else {
            readContacts()
        }
    }

    //获取联系人信息
    private fun readContacts() {
        var cursor: Cursor? = null
        try {
            //查询联系人
            cursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null, null, null, null)
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    //获取联系人姓名
                    val displayName = cursor.getString(cursor.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
                    ))
                    //获取联系人电话
                    val displayPhoneNumber = cursor.getString(cursor.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.NUMBER
                    ))
                    mContactsList.add(displayName + "\n" + displayPhoneNumber)
                }
                mAdapter.notifyDataSetChanged()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            cursor?.close()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>,
                                            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            1 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    readContacts()
                } else {
                    Toast.makeText(this, "You denied the permission", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
