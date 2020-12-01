package com.example.hands_market

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import java.io.ByteArrayOutputStream

class ReviewAdapter(val context: Context, val reviewList: List<Review>): RecyclerView.Adapter<ReviewAdapter.ViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.goods_review_recycler_view, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.reviewTitle.text = reviewList[position].reviewTitle
        viewHolder.reviewBody.text = reviewList[position].reviewBody
    }


    override fun getItemCount() : Int{
        Log.d("reviewListAdapter","00000000")
        return reviewList.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val reviewUser: TextView = view.findViewById(R.id.review_user)
        val reviewTitle: TextView = view.findViewById(R.id.review_title)
        val reviewBody: TextView = view.findViewById(R.id.review_body)
        private val intent: Intent = Intent(context, GoodsDetailActivity::class.java)
        val stream: ByteArrayOutputStream = ByteArrayOutputStream()


        init {
            view.setOnClickListener {

            }
        }
    }
}