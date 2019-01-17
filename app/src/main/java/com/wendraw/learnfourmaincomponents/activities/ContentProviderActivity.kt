package com.wendraw.learnfourmaincomponents.activities


import android.Manifest
import android.content.ContentUris
import android.content.ContentValues
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.ContactsContract.CommonDataKinds.*
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.Toast
import com.wendraw.learnfourmaincomponents.R
import kotlinx.android.synthetic.main.activity_content_provider.*
import java.util.*

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
//            readContacts()
        }

        query_btn.setOnClickListener {
            readContacts()
        }

        insert_btn.setOnClickListener {
            insertContact("wendraw", "86-13355550000")
        }
    }

    //获取联系人信息
    private fun readContacts() {
        mContactsList.clear()
        var cursor: Cursor? = null
        try {
            //查询联系人
            cursor = contentResolver.query(Phone.CONTENT_URI,
                    null, null, null, null)
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    //获取联系人姓名
                    val displayName = cursor.getString(cursor.getColumnIndex(
                            Phone.DISPLAY_NAME
                    ))
                    //获取联系人电话
                    val displayPhoneNumber = cursor.getString(cursor.getColumnIndex(
                            Phone.NUMBER
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

    //新增联系人信息
    private fun insertContact(name: String, phoneNumber: String) {
        // 创建一个空的ContentValues
        val values = ContentValues()
        // 向RawContacts.CONTENT_URI执行一个空值插入，
        // 目的是获取系统返回的rawContactId
        val rawContactUri = contentResolver.insert(ContactsContract.RawContacts.CONTENT_URI, values)
        val rawContactId = ContentUris.parseId(rawContactUri)

        //清空数据
        values.clear()
        values.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId)
        //设置内容类型
        values.put(ContactsContract.Data.MIMETYPE, StructuredName.CONTENT_ITEM_TYPE)
        //设置联系人姓名
        values.put(StructuredName.GIVEN_NAME, name)
        // 向联系人URI添加联系人名字
        contentResolver.insert(ContactsContract.Data.CONTENT_URI, values)

        //清空数据
        values.clear()
        values.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId)
        //设置电话类型
        values.put(ContactsContract.Data.MIMETYPE, Phone.CONTENT_ITEM_TYPE)
        //设置联系人电话
        values.put(Phone.NUMBER, phoneNumber)
        // 向联系人URI添加联系人电话
        contentResolver.insert(ContactsContract.Data.CONTENT_URI, values)

        Toast.makeText(this, "联系人数据添加成功", Toast.LENGTH_SHORT)
                .show()
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
