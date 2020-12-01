package com.example.hands_market

import android.content.Context
import android.content.res.AssetManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import java.io.InputStream
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList
import kotlin.reflect.typeOf

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
class ReivewFragment : Fragment() {
    private var viewAdapter: ReviewAdapter? = null
    private lateinit var fragLayoutManager: RecyclerView.LayoutManager
    private val reviewList : MutableList<Review> = ArrayList()
    private lateinit var recyclerView : RecyclerView

    private lateinit var database : FirebaseDatabase
    lateinit var Review : DatabaseReference
    lateinit var sid :String
    lateinit var gid :String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sid=getArguments()?.getString("sId").toString()
        Log.d("reviewFragggg", "$sid")

        gid=getArguments()?.getString("gId").toString()
        Log.d("reviewFragggg", "$gid")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.goods_review_fragment, container, false)
        fragLayoutManager = LinearLayoutManager(activity)
//        fragLayoutManager = GridLayoutManager(activity, 2)
        val context : Context = view.context


        database = FirebaseDatabase.getInstance() //데이터베이스 부르기
        if(sid!=null && gid!=null){
            Review = database.getReference().child("Stores").child(sid).child("Goods").child(gid).child("Review") //Store 테이블에 접근
            Review.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    var i = 0
                    var reviewTitle: String
                    var reviewBody: String
                    reviewList.clear()
                    for (data in dataSnapshot.children) {
                        var reviewKey = data.key
                        var map = data.value as Map<String, Any>
                        reviewTitle = map["reviewTitle"].toString()
                        reviewBody = map["reviewBody"].toString()
                        reviewList.add(Review("2",sid,gid,reviewKey,reviewTitle,reviewBody,"5"))
                        i = i + 1
                    }
                    viewAdapter?.notifyDataSetChanged()
                }
                override fun onCancelled(error: DatabaseError) {
                }
            })
            sId= ""
        }
        else{
            Log.d("review", "no")
//                goodsList.add(0, Goods("0", "0", "0", null, 10000, null, "100", "RED", 100, "11", null, null))
        }

//        for (i in 0 until 10) {
//            Log.d("review","$i")
//            reviewList.add(Review("2","2","2","1","1","1","1"))
//        }

        viewAdapter = ReviewAdapter(context, reviewList)
        recyclerView = view.findViewById(R.id.goods_review_recycler_view)
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = fragLayoutManager
            adapter = viewAdapter
        }
        Log.d("review","-----------------------------------6")
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
