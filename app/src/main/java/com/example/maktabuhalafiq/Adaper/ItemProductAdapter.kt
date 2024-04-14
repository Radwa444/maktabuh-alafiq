package com.example.maktabuhalafiq.Adaper

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.maktabuhalafiq.databinding.ItemProductBinding
import com.example.maktabuhalafiq.models.ItemPorduct

class ItemProductAdapter(private val itemList: List<ItemPorduct>): RecyclerView.Adapter<ItemProductAdapter.ItemProderViewHolder>() {
    class ItemProderViewHolder(private val binding:ItemProductBinding):RecyclerView.ViewHolder(binding.root) {
     fun bind(itemPorduct: ItemPorduct) {
         binding.imageItem.setImageResource(itemPorduct.image)
         binding.nameProduct.setText(itemPorduct.nameBook)
         binding.nameAuthor.setText(itemPorduct.author)
         binding.price.setText(itemPorduct.price)

     }

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemProderViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemProderViewHolder(binding)
    }

    override fun getItemCount(): Int {
      return itemList.size
    }

    override fun onBindViewHolder(holder: ItemProderViewHolder, position: Int) {
        var itemPorduct:ItemPorduct=itemList[position]
        holder.bind(itemPorduct)
    }

}