package com.example.hands_market

import android.graphics.Bitmap
import java.util.*

class GoodsReview(
    var userID: String, //등록id
    var storeId:String, //가게이름
    var goodsId:String, //물건이름
    var reviewTitle:String?=null,
    var reviewBody:String?=null,
    var reviewDate:Date,
    var reviewStar:String?=null,
    var rId:String?=null, //리뷰뷰 id
) {


}