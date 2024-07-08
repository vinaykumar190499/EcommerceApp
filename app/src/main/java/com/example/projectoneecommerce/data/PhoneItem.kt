package com.example.projectoneecommerce.data

import androidx.annotation.DrawableRes

interface PhoneItem {
    val id:Long?
    var name:String
    var description:String
    var price:Double
    var phoneType:String
    @get:DrawableRes
    var img:Int
    var rating:Float
}