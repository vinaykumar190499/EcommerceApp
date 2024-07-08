package com.example.projectoneecommerce.smartphones

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projectoneecommerce.data.SmartPhones
import com.example.projectoneecommerce.databinding.ActivitySmartPhonesInStoreBinding
import com.example.projectoneecommerce.databinding.SmartphoneItemsBinding

class SmartphonesAdapter(
    val smartphonesLst: MutableList<SmartPhones>,
    val onSelectPhone: (SmartPhones) -> Unit,
    val onSelctAddToCart: (SmartPhones) -> Unit
):RecyclerView.Adapter<SmartphonesAdapter.SmartphonesViewHolder>() {
    private lateinit var binding:SmartphoneItemsBinding
    private lateinit var binding1: ActivitySmartPhonesInStoreBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SmartphonesViewHolder {
        binding = SmartphoneItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        binding1 = ActivitySmartPhonesInStoreBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SmartphonesViewHolder(binding)
    }

    override fun getItemCount(): Int = smartphonesLst.size

    override fun onBindViewHolder(holder: SmartphonesViewHolder, position: Int) {
        holder.bind(smartphonesLst[position])
    }

    inner class SmartphonesViewHolder(binding: SmartphoneItemsBinding) :RecyclerView.ViewHolder(binding.root) {
        fun bind(smartPhones: SmartPhones) {
            with(binding){
                txtPhoneName.text = smartPhones.name
                txtPhoneDescription.text= smartPhones.description
                val pricetext = "$ ${ smartPhones.price }"
                txtPhonePrice.text = pricetext
                smartphoneImg.setImageResource(smartPhones.img)
            }
            binding.smartphonesCard.setOnClickListener{
                onSelectPhone(smartPhones)
            }
            binding.btnAddToCart.setOnClickListener{
                println("here ______________________________________________________________")
                println("call this shit")
                onSelctAddToCart(smartPhones)
            }
        }

    }
}