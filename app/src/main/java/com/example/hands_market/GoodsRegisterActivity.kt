package com.example.hands_market

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase


class GoodsRegisterActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods_register)

        val goodsCreateBtn = findViewById<TextView>(R.id.goodsCreateBtn)
        goodsCreateBtn.setOnClickListener(this)

    }
    override fun onClick(v: View?) {
        if (v != null) {
            when(v.id) {
                //R.id.searchBtn
                R.id.goodsCreateBtn -> {
                    val intent = Intent(this, GoodsDetailActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}