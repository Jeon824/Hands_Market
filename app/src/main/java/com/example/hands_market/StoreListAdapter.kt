package com.example.hands_market

import android.content.Context
import android.content.Intent
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

class StoreListAdapter(val context: Context, val storeList: List<Store>): RecyclerView.Adapter<StoreListAdapter.ViewHolder>(){

    private val inflater: LayoutInflater = LayoutInflater.from(context)



    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder{
        val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.store_recycler_view, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.storeName.text = storeList[position].storeName
        viewHolder.storeAddress.text = storeList[position].storeAddress
        if(storeList[position].storeImg == null)
            viewHolder.storeImage.setImageResource(R.drawable.ic_baseline_storefront_24)
        else
            viewHolder.storeImage.setImageBitmap(storeList[position].storeImg)

    }



    override fun getItemCount() = storeList.size

    class ViewHolder(view: View) :RecyclerView.ViewHolder(view) ,View.OnClickListener{
        val storeName: TextView = view.findViewById(R.id.storeName)
        val storeAddress: TextView = view.findViewById(R.id.storeAddress)
        val storeImage: ImageView = view.findViewById(R.id.storeImage)
        val context = view.context



        override fun onClick(v: View?) {

            val intent : Intent = Intent(context,StoreDetailActivity::class.java)
            context.startActivity(intent)


        }

    }
}