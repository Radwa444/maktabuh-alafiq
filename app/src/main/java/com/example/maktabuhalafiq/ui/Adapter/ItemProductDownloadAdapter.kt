package com.example.maktabuhalafiq.ui.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.maktabuhalafiq.databinding.ItemProductDownloadBinding
import com.example.maktabuhalafiq.data.models.ItemProductDownload

class ItemProductDownloadAdapter(private val itemList: List<ItemProductDownload>):RecyclerView.Adapter<ItemProductDownloadAdapter.ItemProductDownloadViewHolder>() {
    class ItemProductDownloadViewHolder(private val binding:ItemProductDownloadBinding) :RecyclerView.ViewHolder(binding.root){
fun bind(itemProductDownload: ItemProductDownload){
    binding.Image.setImageResource(itemProductDownload.image)
    binding.author.setText(itemProductDownload.author)
    binding.name.setText(itemProductDownload.name)
}
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemProductDownloadViewHolder {
        val binding = ItemProductDownloadBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemProductDownloadViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return itemList.size
    }

    override fun onBindViewHolder(holder: ItemProductDownloadViewHolder, position: Int) {
        var itemProductDownload: ItemProductDownload =itemList[position]
        holder.bind(itemProductDownload)
    }
}