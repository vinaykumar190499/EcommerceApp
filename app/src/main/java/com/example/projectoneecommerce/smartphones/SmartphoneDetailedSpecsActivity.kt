package com.example.projectoneecommerce.smartphones

import android.content.Intent
import android.os.Bundle
import android.widget.RatingBar
import android.widget.Toast
import android.widget.ViewFlipper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectoneecommerce.R
import com.example.projectoneecommerce.UtilAdapters.UserReviewAdapter
import com.example.projectoneecommerce.Utils
import com.example.projectoneecommerce.cart.CartActivity
import com.example.projectoneecommerce.data.SmartPhones
import com.example.projectoneecommerce.databinding.ActivitySmartphoneDetailedSpecsBinding
import com.example.projectoneecommerce.data.UserReview

class SmartphoneDetailedSpecsActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySmartphoneDetailedSpecsBinding
    private var userReviewLst = mutableListOf<UserReview>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySmartphoneDetailedSpecsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        intiViews()
    }

    private fun intiViews() {
        var smartphoneInfo = intent.extras?.getParcelable<SmartPhones>(Utils.SMART_PHONE_INFO)
        with(binding){
            userReviewLst.add(
                UserReview(
                    userName = "John Parker",
                    userReviewTitle = "Quality Phone in Low Budget",
                    userReviewDescription = "Stunning phone. Good battery backup. Excellent camera quality"
                )
            )
            userReviewLst.add(
                UserReview(
                    userName = "Sammy Kagz",
                    userReviewTitle = "Worth & Best Product",
                    userReviewDescription = "Stunning phone. Good battery backup. Excellent camera quality"
                )
            )

            val ratingBar: RatingBar = customRatingBar
            ratingBar.onRatingBarChangeListener = RatingBar.OnRatingBarChangeListener { _, rating, fromUser ->
                if (fromUser) {
                    smartphoneInfo?.rating=rating
                    Toast.makeText(applicationContext, "Rating: $rating", Toast.LENGTH_SHORT).show()
                    // You can save this rating or use it as needed
                }
            }
            txtPhoneTitle.text = smartphoneInfo?.name
            txtPhoneDesc.text = smartphoneInfo?.description
            smartphoneInfo?.rating?.let {
                val rating = it.toString().toFloatOrNull() ?: 0.0f
                customRatingBar.rating = rating
            } ?: run {
                // Handle the case where smartphoneInfo or its rating is null.
                customRatingBar.rating = 0.0f // or some default value
            }
            smartphoneInfo?.img?.let { phoneImg.setImageResource(it) }
            txtPhonePricee.text = "$ ${smartphoneInfo?.price}"

            var sIntent = Intent(this@SmartphoneDetailedSpecsActivity, CartActivity::class.java)
            addIntoCart.setOnClickListener{
                sIntent.putExtra(Utils.SMARTPHONE_INFO,(smartphoneInfo) as SmartPhones)
            }
            btnGotoCart.setOnClickListener{
                startActivity(sIntent)
            }
            recyclerViewUserReview.layoutManager = LinearLayoutManager(this@SmartphoneDetailedSpecsActivity)
            recyclerViewUserReview.adapter=UserReviewAdapter(userReviewLst)
        }

    }

}