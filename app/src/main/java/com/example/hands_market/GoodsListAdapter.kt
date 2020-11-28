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

class GoodsListAdapter(val context: Context, val goodsList: List<Goods>): RecyclerView.Adapter<GoodsListAdapter.ViewHolder>(){

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder{
        val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.goods_recycler_view, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.goodsName.text = goodsList[position].name
        viewHolder.goodsPrice.text = goodsList[position].price.toString()
        if(goodsList[position].image == null)
            viewHolder.goodsImage.setImageResource(R.drawable.ic_baseline_temp_goods_24)
        else
            viewHolder.goodsImage.setImageBitmap(goodsList[position].image)

    }

    override fun getItemCount() = goodsList.size

    inner class ViewHolder(view: View) :RecyclerView.ViewHolder(view){
        val goodsName: TextView = view.findViewById(R.id.goods_name)
        val goodsPrice: TextView = view.findViewById(R.id.goods_price)
        val goodsImage: ImageView = view.findViewById(R.id.goods_image)
        val stream : ByteArrayOutputStream = ByteArrayOutputStream()
        private val intent: Intent =Intent(context,GoodsDetailActivity::class.java)

        init {/*
            intent.putExtra("managerID",goodsList[adapterPosition].managerID)
            intent.putExtra("storeName",goodsList[adapterPosition].storeName)
            intent.putExtra("name",goodsList[adapterPosition].name)
            intent.putExtra("price",goodsList[adapterPosition].price)
            intent.putExtra("location",goodsList[adapterPosition].location)
            intent.putExtra("size",goodsList[adapterPosition].size)
            intent.putExtra("color",goodsList[adapterPosition].color)
            intent.putExtra("count",goodsList[adapterPosition].count)
            intent.putExtra("favoriteGoods",goodsList[adapterPosition].favoriteGoods)
            intent.putExtra("reserve",goodsList[adapterPosition].reserve)
            intent.putExtra("request",goodsList[adapterPosition].request)

            goodsList[adapterPosition].image?.compress(Bitmap.CompressFormat.PNG,90,stream)
            val image = stream.toByteArray()
            intent.putExtra("image", image)*/


            view.setOnClickListener {
                context.startActivity(intent)
            }
        }
    }
}
