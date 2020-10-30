package com.example.stars.ui.main.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.stars.R
import com.example.stars.databinding.ActorItemRowBinding
import com.example.stars.models.PeopleModel

class PeopleAdapter() : RecyclerView.Adapter<PeopleAdapter.MyViewHolder>() {

    var list: MutableList<PeopleModel> = ArrayList()
    lateinit var action:Actions

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflate: ActorItemRowBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.actor_item_row, parent, false
        )
        return MyViewHolder(
            inflate
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.model = list.get(position)

        holder.itemView.setOnClickListener(View.OnClickListener {
            action.onItemClick(list.get(position).id)
         })
    }


    class MyViewHolder(val binding: ActorItemRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    interface Actions {
        fun onItemClick(id:String)

    }
}