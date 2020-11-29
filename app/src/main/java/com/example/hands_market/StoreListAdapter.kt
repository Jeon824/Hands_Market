package com.example.hands_market

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.io.ByteArrayOutputStream
import java.net.URL


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
        else{
//            var storeImgUrlChange = URL(storeList[position].storeImgurl) //url
//            var storeImgBitmap=BitmapFactory.decodeStream(storeImgUrlChange.openStream())//url을 bitmap으로 바꿔주기
//            viewHolder.storeImage.setImageBitmap(storeImgBitmap)
            viewHolder.storeImage.setImageBitmap(storeList[position].storeImg)
        }
    }

    override fun getItemCount() = storeList.size

    inner class ViewHolder(view: View) :RecyclerView.ViewHolder(view){
        val storeName: TextView = view.findViewById(R.id.store_name)
        val storeAddress: TextView = view.findViewById(R.id.store_address)
        val storeImage: ImageView = view.findViewById(R.id.store_image)
        private val intent: Intent =Intent(context, StoreDetailActivity::class.java)
        val stream : ByteArrayOutputStream = ByteArrayOutputStream()

        init {
            view.setOnClickListener {
                intent.putExtra("managerID", storeList[adapterPosition].managerID)
                intent.putExtra("storeName", storeList[adapterPosition].storeName)
                intent.putExtra("storeLat", storeList[adapterPosition].storeLat)
                intent.putExtra("storeLng", storeList[adapterPosition].storeLng)
                intent.putExtra("storeAddress", storeList[adapterPosition].storeAddress)
                intent.putExtra("storeImgUrl", storeList[adapterPosition].storeImgurl)

                //val bitmap = (image.getDrawable() as BitmapDrawable).getBitmap()

                storeList[adapterPosition].storeImg?.compress(Bitmap.CompressFormat.PNG, 90, stream)
                val storeImg = stream.toByteArray()
                intent.putExtra("storeImg", storeImg)
//                storeList[adapterPosition].storeLayout?.compress(Bitmap.CompressFormat.PNG,90,stream)
                val storeLayout = stream.toByteArray()
                intent.putExtra("storeLayout", storeLayout)

//                var storeImgUrlChange = URL(storeList[adapterPosition].storeImgurl)
//                var storeImgBitmap=BitmapFactory.decodeStream(storeImgUrlChange.openConnection().getInputStream())
//                storeImgBitmap.compress(Bitmap.CompressFormat.PNG, 90, stream)
//                val storeImgUrl = stream.toByteArray()
//                intent.putExtra("storeImgUrl", storeImgUrl)
                context.startActivity(intent)
            }
        }
    }
}