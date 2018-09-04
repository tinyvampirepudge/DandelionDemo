package com.tinytongtong.dandelion.biz.account

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.tinytongtong.dandelion.R
import com.tinytongtong.dandelion.biz.grouplist.ApkGroupsListActivity
import com.tinytongtong.dandelion.biz.upload.UploadApkActivity
import kotlinx.android.synthetic.main.activity_account.*

/**
 * @Description: 账户信息
 *
 * @Author wangjianzhou@qding.me
 * @Version
 * @Date 2018/9/4 11:50 AM
 */
class AccountActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)

        btn_upload.setOnClickListener {
            startActivity(Intent(this, UploadApkActivity::class.java))
        }

        btn_apk_list.setOnClickListener {
            startActivity(Intent(this, ApkGroupsListActivity::class.java))
        }
    }
}
