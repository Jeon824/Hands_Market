package com.example.hands_market

import android.graphics.Bitmap

class Store(
    var managerID: String? ="",
    var storeName: String?="",
    var storeLat: Double?,
    var storeLng: Double?,
    var storeAddress: String?="",
    var storeImg: Bitmap? = null,
    var storeLayout :Bitmap? = null,
    var storeImgurl:String? ="",
    var SID: String? ="",
    var storeLayoutUrl:String? ="",
    var favoriteStore: Array<String>?=null
) {


}