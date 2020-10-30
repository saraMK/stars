package com.example.stars.ui.main.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.stars.R
import com.example.stars.databinding.ActorItemRowBinding
import com.example.stars.databinding.ImageItemBinding
import com.example.stars.models.ProfileModel
import com.example.stars.network.service.ORIGIN_BASE_IMAGE_URL

class ImagesAdapter () : RecyclerView.Adapter<ImagesAdapter.MyViewHolder>() {

    var list: MutableList<ProfileModel> = ArrayList()
    lateinit var action:Actions

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflate: ImageItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.image_item, parent, false
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
            action.onItemClick(list.get(position).file_path)
        })
    }


    class MyViewHolder(val binding: ImageItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    interface Actions {
        fun onItemClick(path:String)

    }
}