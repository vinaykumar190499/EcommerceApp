package com.example.projectoneecommerce.UtilAdapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projectoneecommerce.data.UserReview
import com.example.projectoneecommerce.databinding.UserReviewItemBinding

class UserReviewAdapter(var userReviewLst: MutableList<UserReview>):RecyclerView.Adapter<UserReviewAdapter.UserReviewViewHolder>() {
    private lateinit var binding: UserReviewItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserReviewViewHolder {
        binding = UserReviewItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return UserReviewViewHolder(binding)
    }

    override fun getItemCount(): Int = userReviewLst.size

    override fun onBindViewHolder(holder: UserReviewViewHolder, position: Int) {
        holder.bind(userReviewLst[position])
    }

    inner class UserReviewViewHolder(binding: UserReviewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(userReview: UserReview) {
            with(binding){
                userNameReview.text = userReview.userName
                reviewTitle.text = userReview.userReviewTitle
                txtUserReviewDescription.text = userReview.userReviewDescription
            }
        }

    }
}