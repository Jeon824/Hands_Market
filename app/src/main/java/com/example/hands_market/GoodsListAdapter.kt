package com.example.hands_market

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import java.io.ByteArrayOutputStream
import java.net.URL

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
        var aa =goodsList[position].price.toString()
        Log.d("goodListAdapter", "$aa")
        if(goodsList[position].imageUrl == "0")//image
            viewHolder.goodsImage.setImageResource(R.drawable.ic_baseline_temp_goods_24)
        else{
            Log.d("goodListAdapter", "------------------")
            var goodsImg = goodsList[position].imageUrl
            Log.d("goodListAdapter", "$goodsImg")
            var image_task: StoreListAdapter.URLtoBitmapTask = StoreListAdapter.URLtoBitmapTask()
            Log.d("goodListAdapter", "1")
            image_task = StoreListAdapter.URLtoBitmapTask().apply {
                url = URL("$goodsImg")
                Log.d("StoreListAdapter", "$url")
            }

            Log.d("goodListAdapter", "10")
            var bitmap: Bitmap = image_task.execute().get()
            bitmap = Bitmap.createScaledBitmap(bitmap, 200, 200, true);
            viewHolder.goodsImage.setImageBitmap(bitmap)
        }

//            viewHolder.goodsImage.setImageBitmap(goodsList[position].image)

    }

    override fun getItemCount() : Int{
        Log.d("GoodsListAdapter","00000000")
        var bb =goodsList.size
        Log.d("GoodsListAdapter","$bb")
        return goodsList.size
    }

    inner class ViewHolder(view: View) :RecyclerView.ViewHolder(view){
        val goodsName: TextView = view.findViewById(R.id.goods_name)
        val goodsPrice: TextView = view.findViewById(R.id.goods_price)
        val goodsImage: ImageView = view.findViewById(R.id.goods_image)
        val stream : ByteArrayOutputStream = ByteArrayOutputStream()
        private val intent: Intent =Intent(context,GoodsDetailActivity::class.java)

        init {
            view.setOnClickListener {
                intent.putExtra("managerID",goodsList[adapterPosition].managerID)
                intent.putExtra("storeId",goodsList[adapterPosition].storeId)
                intent.putExtra("Gname",goodsList[adapterPosition].name)
                intent.putExtra("Gprice",goodsList[adapterPosition].price)
                intent.putExtra("Glocation",goodsList[adapterPosition].location)
                intent.putExtra("Gsize",goodsList[adapterPosition].size)
                intent.putExtra("Gcolor",goodsList[adapterPosition].color)
                intent.putExtra("Gcount",goodsList[adapterPosition].count)
                intent.putExtra("GimageUrl",goodsList[adapterPosition].imageUrl)
                intent.putExtra("GoodsKey", goodsList[adapterPosition].gId)
//                intent.putExtra("favoriteGoods",goodsList[adapterPosition].favoriteGoods)
//                intent.putExtra("reserve",goodsList[adapterPosition].reserve)
//                intent.putExtra("request",goodsList[adapterPosition].request)

                goodsList[adapterPosition].image?.compress(Bitmap.CompressFormat.PNG,90,stream)
                val image = stream.toByteArray()
                intent.putExtra("Gimage", image)
                context.startActivity(intent)
            }
        }
    }
}
