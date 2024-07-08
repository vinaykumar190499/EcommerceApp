package com.example.projectoneecommerce.data

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class SmartPhones(
    override val id: Long?,
    override var name: String,
    override var description: String,
    override var price: Double,
    @DrawableRes override var img: Int,
    override var phoneType: String,
    override var rating: Float

) : Parcelable, PhoneItem