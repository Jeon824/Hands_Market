package com.example.hands_market

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.reflect.typeOf

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
class GoodsListFragment : Fragment() {
    private var viewAdapter: GoodsListAdapter? = null
    private lateinit var fragLayoutManager: RecyclerView.LayoutManager
    private val goodsList : MutableList<Goods> = ArrayList()
    private lateinit var recyclerView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var searchKeyword : String? = arguments?.getString("keyword")
        val view = inflater.inflate(R.layout.fragment_goods_list, container, false)
        fragLayoutManager = GridLayoutManager(activity,2)
        val context : Context = view.context

        for (i in 0 until 10)
            goodsList.add(i,Goods("관리자1","가게이름","$i 번째 상품",null,10000 ,null,100,"RED",100))

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
