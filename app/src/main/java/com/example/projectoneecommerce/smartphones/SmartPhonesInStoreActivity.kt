package com.example.projectoneecommerce.smartphones

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectoneecommerce.R
import com.example.projectoneecommerce.Utils
import com.example.projectoneecommerce.cart.CartActivity
import com.example.projectoneecommerce.data.SmartPhones
import com.example.projectoneecommerce.databinding.ActivitySmartPhonesInStoreBinding

class SmartPhonesInStoreActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySmartPhonesInStoreBinding
    private lateinit var smartphonesadapter: SmartphonesAdapter
    private var smartphonesLst = mutableListOf<SmartPhones>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySmartPhonesInStoreBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    fun initViews(){
        with(binding){
            smartphonesLst.add(
                SmartPhones(
                    id = 1,
                    name = "Apple iPhone 13 Pro Max ",
                    description = "iPhone 13 Pro Max. Forged in titanium and featuring the groundbreaking A17 Pro chip, a customizable Action button, and the most powerful iPhone camera system ever.",
                    price = 1099.99,
                    img=R.drawable.phoneimg,
                    phoneType = "ios",
                    rating = 2.5f
                )
            )
            smartphonesLst.add(
                SmartPhones(
                    id = 2,
                    name = "Apple iPhone 12 Pro Max ",
                    description = "iPhone 12 Pro Max. Forged in titanium and featuring the groundbreaking A17 Pro chip, a customizable Action button, and the most powerful iPhone camera system ever.",
                    price = 999.99,
                    img=R.drawable.phoneimg2,
                    phoneType = "ios",
                    rating = 3.0f
                )
            )
            smartphonesLst.add(
                SmartPhones(
                    id = 3,
                    name = "Apple iPhone 11 Pro Max ",
                    description = "iPhone 11 Pro Max. Forged in titanium and featuring the groundbreaking A17 Pro chip, a customizable Action button, and the most powerful iPhone camera system ever.",
                    price = 1199.99,
                    img=R.drawable.phoneimg3,
                    phoneType = "ios",
                    rating = 1.5f
                )
            )
            val sintent = Intent(this@SmartPhonesInStoreActivity, SmartphoneDetailedSpecsActivity::class.java)
            var cIntent = Intent(this@SmartPhonesInStoreActivity, CartActivity::class.java)
            recyclerViewSmartPhones.layoutManager = LinearLayoutManager(this@SmartPhonesInStoreActivity)
            smartphonesadapter = SmartphonesAdapter(smartphonesLst,{ smartphones ->
                sintent.putExtra(Utils.SMART_PHONE_INFO, smartphones)
                startActivity(sintent)
            },
                {
                    smartphones->
                    cIntent.putExtra(Utils.SMARTPHONE_INFO,smartphones)
                }
            )
            btnGoToCart.setOnClickListener {
                startActivity(cIntent)
            }
            recyclerViewSmartPhones.adapter = smartphonesadapter
        }
    }

}