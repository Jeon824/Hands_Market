package com.example.hands_market

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

//import com.google.firebase.database.DatabaseReference
//import com.google.firebase.database.FirebaseDatabase

class StoreRegistActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var marketNameInput :EditText
    private var sid="store"
    //private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_regist)
        val storeCreateBtn = findViewById<TextView>(R.id.storeCreateBtn)
        storeCreateBtn.setOnClickListener(this)

        val database = Firebase.database
        val myRef = database.getReference("message")

        myRef.setValue("Hello, World!")

    }

    override fun onClick(v: View?) {
        if (v != null) {
            when(v.id) {
                //R.id.searchBtn
                R.id.storeCreateBtn -> {
//                    val database : FirebaseDatabase = FirebaseDatabase.getInstance()
//                    val myRef = database.getReference()
//                    println(myRef)
//                    println(marketNameInput.text.toString())
//                    val StoreOne = Store(
//                        storeName= marketNameInput.text.toString(),
//                        managerID = "1",
//                        storeAddress = "2"
//                    )

//                    println(StoreOne)
//                    myRef.setValue(StoreOne)
                    //Toast.makeText(StoreDetailActivity.this, "저장을 완료했습니다.", Toast.LENGTH_SHORT).show();

                    val intent = Intent(this, StoreDetailActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}