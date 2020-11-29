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
import android.widget.LinearLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.io.InputStream
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
class StoreListFragment : Fragment() {
    private var viewAdapter: StoreListAdapter? = null
    private lateinit var fragLayoutManager: RecyclerView.LayoutManager
    private val storeList : MutableList<Store> = ArrayList()
    private lateinit var recyclerView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var searchKeyword : String? = arguments?.getString("keyword")
        val view = inflater.inflate(R.layout.fragment_store_list, container, false)
        if(arguments?.getString("type")=="favorite")
        {
            var type: String? = arguments?.getString("type")
            if (type == "favorite")
                fragLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            else
                fragLayoutManager = GridLayoutManager(activity,2)
        }
        else
        {
            fragLayoutManager = GridLayoutManager(activity,2)
        }

        val context : Context = view.context
        val am : AssetManager = resources.assets;
        var inS :InputStream? = null
        var tmpLayout : Bitmap? = null
        try{
            inS = am.open("dummy_layout.png")
            tmpLayout = BitmapFactory.decodeStream(inS)
        }catch(e: Exception) {
            e.printStackTrace()
        }

//        // Store 목록 조회
//        val database : FirebaseDatabase = FirebaseDatabase.getInstance() //데이터베이스 부르기
//        val Stores = database.getReference().child("Stores") //Store 테이블에 접근
//        Stores.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                var i=0
//                var storeN: String
//                var storeImgUrl: String
//                for(data in dataSnapshot.children){
//                    Log.d("StoreListFragment","in!!!!!!!!!!!!!!!!!!!")
//                    var storeKey =data.key
//                    Log.d("StoreListFragment","$storeKey")
//                    var map =data.value as Map<String,Any>
//                    Log.d("StoreListFragment","$map")
//                    storeN = map["storeName"].toString()
//                    storeImgUrl = map["storeImgurl"].toString()
//                    storeList.add(i, Store("$i 번째 매니저", storeN, i * 0.1, i * 0.1, "$i 번째 주소", null, null,storeImgUrl,storeKey))
//                    if(storeList[i].storeLayout == null)
//                        storeList[i].storeLayout = tmpLayout
//                    i=i+1
//                    Log.d("StoreListFragment","success")
//                }
//            }
//            override fun onCancelled(error: DatabaseError) {
//            }
//        })


        for (i in 0 until 10) {
            storeList.add(i, Store("$i 번째 매니저", "$i 번째 매장", i * 0.1, i * 0.1, "$i 번째 주소", null, null))
            if(storeList[i].storeLayout == null)
                storeList[i].storeLayout = tmpLayout
        }

        viewAdapter = StoreListAdapter(context, storeList)

        recyclerView = view.findViewById(R.id.store_recycler_view)
        recyclerView.layoutParams.width = LinearLayout.LayoutParams.MATCH_PARENT/2
        recyclerView.layoutParams.height = LinearLayout.LayoutParams.MATCH_PARENT/2
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = fragLayoutManager
            adapter = viewAdapter
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


}
