package com.example.maktabuhalafiq.ui.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.maktabuhalafiq.data.models.Categories
import com.example.maktabuhalafiq.databinding.ButtonCategoryBinding

class ButtonCategoriesAdapter(private val onItemClick: (Categories) -> Unit) :
    ListAdapter<Categories, ButtonCategoriesAdapter.ButtonCategoryHolder>(CategoryDiffCallback()) {

    inner class ButtonCategoryHolder(private val binding: ButtonCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(category: Categories) {
            binding.button.text = category.name
            binding.root.setOnClickListener { onItemClick(category) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ButtonCategoryHolder {
        val binding = ButtonCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ButtonCategoryHolder(binding)
    }

    override fun onBindViewHolder(holder: ButtonCategoryHolder, position: Int) {
        val category = getItem(position)
        holder.bind(category)
    }

    private class CategoryDiffCallback : DiffUtil.ItemCallback<Categories>() {
        override fun areItemsTheSame(oldItem: Categories, newItem: Categories): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Categories, newItem: Categories): Boolean {
            return oldItem == newItem
        }
    }
}
