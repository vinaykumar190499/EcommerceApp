package com.example.projectoneecommerce

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.projectoneecommerce.data.DashBoard
import com.example.projectoneecommerce.databinding.ActivityNavigateToCategoryBinding
import com.example.projectoneecommerce.smartphones.SmartPhonesInStoreActivity
import com.example.projectoneecommerce.smartphones.SmartphoneDetailedSpecsActivity

class NavigateToCategoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNavigateToCategoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavigateToCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        intiviews()
    }

    private fun intiviews() {
        var dashBoardItem = intent.extras?.getParcelable<DashBoard>(Utils.DASHBOARD_CATEGORY_INFO)
        println("Here____________________________________________")
        println(dashBoardItem?.category?.toLowerCase())

        if(dashBoardItem?.category?.toLowerCase().equals("smart phones")){
            startActivity(
                Intent(this@NavigateToCategoryActivity,SmartPhonesInStoreActivity::class.java)
            )
        }
        if(dashBoardItem?.category?.toLowerCase() == "laptop"){

        }
        if(dashBoardItem?.category?.toLowerCase() == "mens ware"){

        }
        if(dashBoardItem?.category?.toLowerCase() == "womens ware"){

        }
        if(dashBoardItem?.category?.toLowerCase() == "kids ware"){

        }
        if(dashBoardItem?.category?.toLowerCase() == "groceries"){

        }
    }
}
