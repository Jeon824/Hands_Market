package com.example.hands_market

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
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

class ReviewAdapter(val context: Context, val storeList: List<Review>): RecyclerView.Adapter<ReviewAdapter.ViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.store_recycler_view, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

    }


    override fun getItemCount() = storeList.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val storeName: TextView = view.findViewById(R.id.storeName)
        val storeAddress: TextView = view.findViewById(R.id.storeAddress)
        val storeImage: ImageView = view.findViewById(R.id.storeImage)
        private val intent: Intent = Intent(context, StoreDetailActivity::class.java)
        val stream: ByteArrayOutputStream = ByteArrayOutputStream()


        init {

        }
    }
}