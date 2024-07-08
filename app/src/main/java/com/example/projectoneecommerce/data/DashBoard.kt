package com.example.projectoneecommerce.data

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class DashBoard (
    @DrawableRes var categoryImg:Int,
    var category: String
):Parcelable