package com.example.projectoneecommerce.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.projectoneecommerce.DatabaseHelper
import com.example.projectoneecommerce.databinding.CartItemsBinding

class CartAdapter(
    private val cartItemsList: List<CartItem>,
    val databaseHelper: DatabaseHelper
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    private lateinit var binding: CartItemsBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {

        binding = CartItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun getItemCount(): Int = cartItemsList.size

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(cartItemsList[position])
    }

    inner class CartViewHolder(binding: CartItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(cartItem: CartItem) {
            with(binding) {
                var price = cartItem.item.price
                var quantity = cartItem.quantity
                txtCartItemPrice.text = price.toString()
                txtCartItemName.text = cartItem.item.name
                txtCartItemDescription.text = cartItem.item.description
                cartItemImg.setImageResource(cartItem.item.img)
                txtCartItemQuantity.text= quantity.toString()
                txtCartItemTotal.text = (price * quantity).toString()
                btnCartItemAddQuantity.setOnClickListener {
                    cartItem.id?.let { id ->
                        val rowsUpdated = databaseHelper.incrementProductQuantity(id)
                        if (rowsUpdated > 0) {
                            cartItem.quantity++
                            quantity++
                            txtCartItemQuantity.text = quantity.toString()
                            txtCartItemTotal.text = (price * cartItem.quantity).toString()
                            notifyItemChanged(adapterPosition)
                            (itemView.context as CartActivity).updateTotalPriceSum()
                        }
                    }
                }
                btnCartItemSubQuantity.setOnClickListener {
                    cartItem.id?.let { id ->
                        val rowsUpdated = databaseHelper.decrementProductQuantity(id)
                        if (rowsUpdated > 0) {
                            cartItem.quantity--
                            quantity--
                            txtCartItemQuantity.text = quantity.toString()
                            txtCartItemTotal.text = (price * cartItem.quantity).toString()
                            notifyItemChanged(adapterPosition)
                            (itemView.context as CartActivity).updateTotalPriceSum()
                        } else {
                            Toast.makeText(itemView.context, "Minimum quantity reached", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }

        }
    }
}