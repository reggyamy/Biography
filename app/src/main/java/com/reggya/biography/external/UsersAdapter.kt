package com.reggya.biography.external

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.reggya.biography.data.model.UsersResponseItem
import com.reggya.biography.databinding.UserItemBinding
import java.text.SimpleDateFormat
import java.util.Locale

class UsersAdapter: RecyclerView.Adapter<UsersAdapter.ViewHolder>() {
    
    private val users = ArrayList<UsersResponseItem?>()
    lateinit var itemOnClickListener : ((item: UsersResponseItem) -> Unit)
    @SuppressLint("NotifyDataSetChanged")
    fun updateData(users: List<UsersResponseItem?>?){
        if (users == null) return
        this.users.clear()
        this.users.addAll(users)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
    
    override fun getItemCount() = users.size
    
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = users[position]
        if (data != null) {
            holder.bind(data)
        }
    }

    inner class ViewHolder(private val binding: UserItemBinding): RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SimpleDateFormat")
        fun bind(data: UsersResponseItem) {
            val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
            if (!data.createdAt.isNullOrEmpty()) {
                val date = formatter.parse(data.createdAt)
                val desiredFormat = SimpleDateFormat("dd, MMM yyyy").format(date)
                binding.date.text = desiredFormat
            }
            Glide.with(itemView.context).load(data.avatar).into(binding.image)
            binding.title.text = data.name

            itemView.setOnClickListener {
                itemOnClickListener.invoke(data)
            }
        }
    }
}
