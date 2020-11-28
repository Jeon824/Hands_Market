package com.example.hands_market

import android.graphics.Bitmap
import java.net.IDN

class Goods(var managerID: String, //관리자id            
            var storeName:String, //가게이름
            var name:String,//상품이름
            var image:Bitmap?,//가게 이미지
            var price:Int,//가격
            var location: String?, //물건 위치
            var size : Int,//사이즈
            var color : String?=null,//색
            var count : Int?=null,//개수
            var favoriteGoods: Array<String>?=null,//즐겨찾기 한 사람의 목록
            var reserve:Array<String>?=null,//예약한 사람의 목록
            var request:Array<String>?=null//입고요청한 사람의 목록
            ){

    override fun toString(): String {
        return super.toString()
    }
}