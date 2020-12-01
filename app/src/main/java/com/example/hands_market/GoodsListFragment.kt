package com.example.hands_market

import android.content.Context
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

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
class GoodsListFragment : Fragment() {
    private var viewAdapter: GoodsListAdapter? = null
    private lateinit var fragLayoutManager: RecyclerView.LayoutManager
    private val goodsList : MutableList<Goods> = ArrayList()
    private lateinit var recyclerView : RecyclerView
    private lateinit var database : FirebaseDatabase
    lateinit var Goods : DatabaseReference
    lateinit var sId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sId=getArguments()?.getString("sId").toString()
        Log.d("GoodsFragggg", "$sId")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        // Inflate the layout for this fragment
        var searchKeyword : String? = arguments?.getString("keyword")
        val view = inflater.inflate(R.layout.fragment_goods_list, container, false)

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


        Log.d("GoodsFragggg", "33")
        database = FirebaseDatabase.getInstance() //데이터베이스 부르기
        if(sId!=null){
            Log.d("GoodsFragggg", "in!!!!!!")
            Goods = database.getReference().child("Stores").child(sId).child("Goods") //Store 테이블에 접근
            Log.d("GoodsFragggg", " -----------------------")
            Goods.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    Log.d("GoodsFragggg", " Goods~~~~~~~~~~~~in!!!!!!")
                    var i = 0
                    var goodsN: String
                    var goodsImgUrl: String
                    goodsList.clear()
                    Log.d("GoodsFragggg", " 1")
                    for (data in dataSnapshot.children) {
                        Log.d("GoodsFragggg", " 2")
                        var goodsKey = data.key
                        Log.d("GoodsFragggg", "$goodsKey")
                        var map = data.value as Map<String, Any>
                        goodsN = map["name"].toString()
                        goodsImgUrl = map["image"].toString()
                        goodsList.add(i, Goods("$i 번째 관리자", "$i 번째 가게", goodsN, null, 10000, null, "100", "RED", 100, goodsImgUrl, null, null))
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
            Log.d("GoodsFragggg", "no")
//                goodsList.add(0, Goods("0", "0", "0", null, 10000, null, "100", "RED", 100, "11", null, null))
        }

        Log.d("GoodsListFragment", "inadapter~~~~~~~~~~~~~~~~~~~~~~~~")

        Log.d("GoodsListFragment", "iin Zero!")
        viewAdapter = GoodsListAdapter(context, goodsList)

        recyclerView = view.findViewById(R.id.goods_recycler_view)
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
