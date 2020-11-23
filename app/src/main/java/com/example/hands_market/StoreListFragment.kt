package com.example.hands_market

import android.content.Context
import android.content.res.AssetManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.InputStream
import java.lang.Exception
import kotlin.reflect.typeOf

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
        fragLayoutManager = GridLayoutManager(activity,2)
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
        for (i in 0 until 10) {
            storeList.add(i, Store("$i 번째 매니저", "$i 번째 매장", i * 0.1, i * 0.1, "$i 번째 주소", null, null))
            if(storeList[i].storeLayout == null)
                storeList[i].storeLayout = tmpLayout
        }

        viewAdapter = StoreListAdapter(context, storeList)

        recyclerView = view.findViewById(R.id.store_recycler_view)
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
