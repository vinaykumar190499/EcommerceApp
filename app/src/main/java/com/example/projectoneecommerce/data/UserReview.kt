package com.example.projectoneecommerce.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserReview (
    var userName:String,
    var userReviewTitle:String,
    var userReviewDescription:String
):Parcelable