package com.example.hands_market

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class StoreRegistActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_regist)

        val storeCreateBtn = findViewById<TextView>(R.id.storeCreateBtn)
        storeCreateBtn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when(v.id) {
                //R.id.searchBtn
                R.id.storeCreateBtn -> {
                    val intent = Intent(this, StoreDetailActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}