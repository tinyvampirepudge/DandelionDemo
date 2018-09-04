package com.tinytongtong.dandelion

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.tinytongtong.dandelion.biz.account.AccountActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_account0.setOnClickListener {
            val intent = Intent(this, AccountActivity::class.java)
            startActivity(intent)
        }
    }
}
