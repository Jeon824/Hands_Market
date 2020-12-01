package com.example.hands_market

import android.content.Context
import android.content.res.AssetManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import java.io.InputStream
import java.util.*
import java.util.concurrent.CountDownLatch
import kotlin.collections.ArrayList


// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
class StoreListFragment : Fragment() {
    private var viewAdapter: StoreListAdapter? = null
    private lateinit var fragLayoutManager: RecyclerView.LayoutManager
    private val storeList : MutableList<Store> = ArrayList()
    private lateinit var recyclerView : RecyclerView
    private lateinit var database : FirebaseDatabase
    lateinit var Stores : DatabaseReference

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
                fragLayoutManager = GridLayoutManager(activity, 2)
        }
        else
        {
            fragLayoutManager = GridLayoutManager(activity, 2)
        }

        val context : Context = view.context
        val am : AssetManager = resources.assets;
        var inS :InputStream? = null
        var tmpLayout : Bitmap? = null
        try{
            inS = am.open("dummy_layout.png")
            tmpLayout = BitmapFactory.decodeStream(inS)
        }catch (e: Exception) {
            e.printStackTrace()
        }

        // Store 목록 조회
        database = FirebaseDatabase.getInstance() //데이터베이스 부르기
        Stores = database.getReference().child("Stores") //Store 테이블에 접근
        Stores.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var i = 0
                var storeN: String
                var storeImgUrl: String
                storeList.clear()
                for (data in dataSnapshot.children) {
                    var storeKey = data.key
                    var map = data.value as Map<String, Any>
                    storeN = map["storeName"].toString()
                    storeImgUrl = map["storeImgurl"].toString()
                    storeList.add(i, Store("$i 번째 매니저", storeN, i * 0.1, i * 0.1, "$i 번째 주소", null, null, storeImgUrl, storeKey))
                    if (storeList[i].storeLayout == null)
                        storeList[i].storeLayout = tmpLayout
                    var aa = storeList[i]
                    i = i + 1
                }
                viewAdapter?.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })



//        for (i in 0 until 10) {
//            storeList.add(i, Store("$i 번째 매니저", "$i 번째 매장", i * 0.1, i * 0.1, "$i 번째 주소", null, null))
//            if(storeList[i].storeLayout == null)
//                storeList[i].storeLayout = tmpLayout
//            var aa =storeList[i]
//            Log.d("StoreListFragment","$aa")
//        }


        Log.d("StoreListFragment", "inadapter~~~~~~~~~~~~~~~~~~~~~~~~")
//        while (storeList==null){
//
//        }


        viewAdapter = StoreListAdapter(context, storeList)

        recyclerView = view.findViewById(R.id.store_recycler_view)
//        recyclerView.layoutParams.width = LinearLayout.LayoutParams.MATCH_PARENT/2
//        recyclerView.layoutParams.height = LinearLayout.LayoutParams.MATCH_PARENT/2
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

//    // You could do it as well generic, that's what I do in my lib:
//    interface SimpleCallback<T> {
//        fun callback(data: T)
//    }
//    private fun checkAnswerSubmission(finishedCallback: SimpleCallback<Boolean>) {
//        val answerDatabase = FirebaseDatabase.getInstance().reference.child("userPuzzleHistory").child(uid)
//        answerDatabase.addListenerForSingleValueEvent(object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                // This will simple call your callback containing a boolean true/false
//                finishedCallback.callback(dataSnapshot.hasChild(java.lang.String.valueOf(imagename)))
//            }
//
//            override fun onCancelled(databaseError: DatabaseError) {}
//        })
//    }




}
