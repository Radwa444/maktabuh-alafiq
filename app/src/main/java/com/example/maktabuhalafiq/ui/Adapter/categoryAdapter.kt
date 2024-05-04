package com.example.maktabuhalafiq.ui.Adapter
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.maktabuhalafiq.data.models.Categories
import com.example.maktabuhalafiq.databinding.ItemCategoryBinding
import com.squareup.picasso.Picasso

class CategoryAdapter(
    private val onItemClick: (Categories) -> Unit
) : ListAdapter<Categories, CategoryAdapter.CategoryViewHolder>(CategoryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCategoryBinding.inflate(inflater, parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = getItem(position)
        holder.bind(category)
    }

    inner class CategoryViewHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(category: Categories) {
            try {
                Log.d("PicassoImageURL", category.image)
                Picasso.get()
                    .load(category.image)
                    .into(binding.imageView)

                // Handle item click
                binding.root.setOnClickListener { onItemClick(category) }

            } catch (e: Exception) {
                Log.e("PicassoError", e.message ?: "Unknown error occurred")
            }
        }
    }

    class CategoryDiffCallback : DiffUtil.ItemCallback<Categories>() {
        override fun areItemsTheSame(oldItem: Categories, newItem: Categories): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Categories, newItem: Categories): Boolean {
            return oldItem == newItem

        }
    }

}
