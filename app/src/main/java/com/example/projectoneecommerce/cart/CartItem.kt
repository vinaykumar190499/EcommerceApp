package com.example.projectoneecommerce.cart

import android.os.Parcelable
import com.example.projectoneecommerce.data.SmartPhones
import kotlinx.parcelize.Parcelize

@Parcelize
data class CartItem (
    val id: Long?,
    var quantity: Int,
    var item: SmartPhones,
    var itemId : Long
):Parcelable