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
class ReivewFragment : Fragment() {
    private var viewAdapter: ReviewAdapter? = null
    private lateinit var fragLayoutManager: RecyclerView.LayoutManager
    private val reviewList : MutableList<Review> = ArrayList()
    private lateinit var recyclerView : RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.goods_review_fragment, container, false)
        fragLayoutManager = LinearLayoutManager(activity)
        val context : Context = view.context

        for (i in 0 until 10) {
            reviewList.add(Review())

        }

        viewAdapter = ReviewAdapter(context, reviewList)

        recyclerView = view.findViewById(R.id.goods_review_recycler_view)
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