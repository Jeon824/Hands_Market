package com.example.hands_market

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class ManagerActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manager)

        val addStoreBtn = findViewById<TextView>(R.id.addStoreBtn)
        addStoreBtn.setOnClickListener(this)

      
    }
    override fun onClick(v: View?) {
        if (v != null) {
            when(v.id) {
                //R.id.searchBtn
                R.id.addStoreBtn -> {
                    val intent = Intent(this, StoreRegistActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}