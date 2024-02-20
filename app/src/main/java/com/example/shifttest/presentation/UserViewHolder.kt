package com.example.shifttest.presentation

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.shifttest.R
import com.example.shifttest.databinding.UserItemBinding
import com.example.shifttest.domain.User

data class UserItem(
    val user: User,
    val onClick: (User) -> Unit
)

class UserViewHolder(private val binding: UserItemBinding)
    : RecyclerView.ViewHolder(binding.root){
    fun bind(userItem: UserItem){
        Glide.with(binding.imageView)
            .load(userItem.user.picture)
            .transform(
                MultiTransformation(
                    CenterCrop(),
                    RoundedCorners(this.itemView.resources
                        .getDimension(R.dimen.corners_radius).toInt())
                )
            ).into(binding.imageView)
        itemView.setOnClickListener {
            userItem.onClick(userItem.user)
        }
        binding.tvName.text = userItem.user.name
        binding.tvAddress.text = userItem.user.address
        binding.tvPhone.text = userItem.user.number
    }
}