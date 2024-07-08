package com.example.projectoneecommerce.checkout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projectoneecommerce.cart.CartItem
import com.example.projectoneecommerce.databinding.CheckoutItemsBinding

class CheckoutAdapter(
    private val checkoutList: ArrayList<CartItem>
) : RecyclerView.Adapter<CheckoutAdapter.CheckoutViewHolder>() {

    private lateinit var binding: CheckoutItemsBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckoutViewHolder {
        binding = CheckoutItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CheckoutViewHolder(binding)
    }

    override fun getItemCount(): Int = checkoutList.size

    override fun onBindViewHolder(holder: CheckoutViewHolder, position: Int) {
        holder.bind(checkoutList[position])
    }

    inner class CheckoutViewHolder(binding: CheckoutItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(checkoutItem: CartItem) {
            with(binding) {
                var unitPrice=checkoutItem.item.price
                txtUnitPriceValue.text = unitPrice.toString()
                var quantity = checkoutItem.quantity
                txtQuantityValue.text = quantity.toString()
                txtTotalAmountValue.text = (unitPrice*quantity).toString()
                checkoutItemImg.setImageResource(checkoutItem.item.img)
            }
        }
    }
}