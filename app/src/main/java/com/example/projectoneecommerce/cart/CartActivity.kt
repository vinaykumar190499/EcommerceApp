package com.example.projectoneecommerce.cart

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectoneecommerce.DatabaseHelper
import com.example.projectoneecommerce.R
import com.example.projectoneecommerce.Utils
import com.example.projectoneecommerce.checkout.CartCheckoutActivity
import com.example.projectoneecommerce.data.SmartPhones
import com.example.projectoneecommerce.databinding.ActivityCartBinding

class CartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCartBinding
    private lateinit var cartAdapter: CartAdapter
    private lateinit var databaseHelper: DatabaseHelper
    private var cartProductsList = mutableListOf<CartItem>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        println("Inside the cart")
        initDatabase()
        fetchData()
        initViews()


    }
    private fun initViews() {
        with(binding){
            recyclerView.layoutManager = LinearLayoutManager(this@CartActivity)
            cartAdapter = CartAdapter(cartProductsList, databaseHelper)
            recyclerView.adapter = cartAdapter
            val itemAddedToCart = intent.extras?.getParcelable<SmartPhones>(Utils.SMARTPHONE_INFO)
            var newItemToCart = itemAddedToCart?.let {
                CartItem(
                    id=null,
                    quantity = 1,
                    item = it,
                    itemId = it.id?:0
                )
            }
//            var newItemToCart = itemAddedToCart?.let {
//                it.id?.let { it1 ->
//                    CartItem(
//                        id=null,
//                        quantity = 1,
//                        item = it,
//                        itemId = it1
//                    )
//                }
//            }

            if (newItemToCart != null) {
                databaseHelper.insertData(newItemToCart)
            }
            if (newItemToCart != null) {
                cartProductsList.add(newItemToCart)
            }
            cartAdapter.notifyItemInserted(cartProductsList.size - 1)
            updateTotalPriceSum()

            ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val position = viewHolder.adapterPosition
                    val cartItem = cartProductsList[position]
                    cartItem.id?.let {
                        val deleted = databaseHelper.deleteProduct(it)

                        if (deleted > 0) {
                            cartProductsList.removeAt(position)
                            cartAdapter.notifyItemRemoved(position)
                            updateTotalPriceSum()
                        }
                    }
                }

                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }
            }).attachToRecyclerView(recyclerView)
            updateTotalPriceSum()
            btnCartCheckout.setOnClickListener {
                var checkOutIntent = Intent(this@CartActivity,CartCheckoutActivity::class.java)
                checkOutIntent.putParcelableArrayListExtra(Utils.CART_ITEMS_LIST, ArrayList(cartProductsList))
                startActivity(checkOutIntent)
            }
        }
    }
    private fun fetchData() = cartProductsList.addAll(databaseHelper.readData())
    private fun initDatabase() {
        databaseHelper = DatabaseHelper(this)
    }
    fun updateTotalPriceSum() {
        var totalSum = 0.0
        for (product in cartProductsList) {
            totalSum += product.item.price * product.quantity
        }
        binding.txtTotalCartCheckOutPriceSum.text = "Total Price: $ $totalSum"
    }
}