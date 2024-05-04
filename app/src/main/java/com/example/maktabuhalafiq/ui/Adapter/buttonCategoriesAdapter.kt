package com.example.maktabuhalafiq.ui.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.maktabuhalafiq.databinding.ButtonCategoryBinding

class ButtonCategoriesAdapter(private val nameCategoriesList: List<String>) : RecyclerView.Adapter<ButtonCategoriesAdapter.ButtonCategoryHolder>() {

    inner class ButtonCategoryHolder(private val binding: ButtonCategoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(category: String) {
            binding.button.text = category

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ButtonCategoryHolder {
        val binding = ButtonCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ButtonCategoryHolder(binding)
    }

    override fun onBindViewHolder(holder: ButtonCategoryHolder, position: Int) {
        val category = nameCategoriesList[position]
        holder.bind(category)
    }

    override fun getItemCount(): Int {
        return nameCategoriesList.size
    }
}