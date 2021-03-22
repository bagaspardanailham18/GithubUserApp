package com.bagas.githubuserapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bagas.githubuserapp.databinding.ItemRowUserBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlin.collections.ArrayList

class ListUserAdapter(val listUser: ArrayList<User>) : RecyclerView.Adapter<ListUserAdapter.UserViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class UserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val binding = ItemRowUserBinding.bind(itemView)

        var tvName = binding.tvItemName
        var tvUsername = binding.tvItemUsername
        var avatar = binding.avatar
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListUserAdapter.UserViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListUserAdapter.UserViewHolder, position: Int) {
        val user = listUser[position]
        Glide.with(holder.itemView.context)
            .load(user.avatar)
            .apply(RequestOptions().override(55,55))
            .into(holder.avatar)
        holder.tvName.text = user.name
        holder.tvUsername.text = user.username
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listUser[holder.adapterPosition]) }
    }

    override fun getItemCount(): Int {
        return listUser.size
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: User)
    }

}