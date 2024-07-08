package com.example.projectoneecommerce.dasboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projectoneecommerce.data.DashBoard
import com.example.projectoneecommerce.databinding.DashboardItemsBinding

class DashBoardAdapter(
    private val dashBoardList: List<DashBoard>,
    private val onSelectCategory: (DashBoard) -> Unit
) : RecyclerView.Adapter<DashBoardAdapter.DashBoardViewHolder>() {

    private lateinit var binding: DashboardItemsBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashBoardViewHolder {
        binding = DashboardItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DashBoardViewHolder(binding)
    }

    override fun getItemCount(): Int = dashBoardList.size

    override fun onBindViewHolder(holder: DashBoardViewHolder, position: Int) {
        holder.bind(dashBoardList[position])
    }

    inner class DashBoardViewHolder(binding: DashboardItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(dashboardItem: DashBoard) {
            with(binding) {
                txtCategory.text = dashboardItem.category
                categoryImg.setImageResource(dashboardItem.categoryImg)
            }
            binding.dashboardCard.setOnClickListener{
                onSelectCategory(dashboardItem)
            }
        }
    }
}