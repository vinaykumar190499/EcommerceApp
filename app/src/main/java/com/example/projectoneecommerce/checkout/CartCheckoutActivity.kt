package com.example.projectoneecommerce.checkout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectoneecommerce.Utils
import com.example.projectoneecommerce.cart.CartItem
import com.example.projectoneecommerce.databinding.ActivityCartCheckoutBinding

class CartCheckoutActivity : AppCompatActivity() {
    private lateinit var binding : ActivityCartCheckoutBinding
    private lateinit var cartItemsList: ArrayList<CartItem>
    private lateinit var cartCheckoutAdapter: CheckoutAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        cartItemsList = intent.getParcelableArrayListExtra(Utils.CART_ITEMS_LIST) ?: arrayListOf()
        initviews()
    }

    private fun initviews() {
        with(binding){
            recyclerView.layoutManager = LinearLayoutManager(this@CartCheckoutActivity)
            cartCheckoutAdapter = CheckoutAdapter(cartItemsList)
            recyclerView.adapter = cartCheckoutAdapter
            updateTotalPriceSum()
        }
    }
    fun updateTotalPriceSum() {
        var totalSum = 0.0
        for (product in cartItemsList) {
            totalSum += product.item.price * product.quantity
        }
        binding.txtTotalCheckOutAmount.text = "$ $totalSum"
    }
}