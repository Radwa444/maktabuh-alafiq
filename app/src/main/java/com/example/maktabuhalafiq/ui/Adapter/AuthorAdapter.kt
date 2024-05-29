package com.example.maktabuhalafiq.ui.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.maktabuhalafiq.data.models.Author
import com.example.maktabuhalafiq.databinding.ItemAuthorBinding
import com.squareup.picasso.Picasso

class AuthorAdapter(private val onItemClick: (Author) -> Unit) :
    ListAdapter<Author, AuthorAdapter.AuthorViewHolder>(AuthorDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AuthorViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemAuthorBinding.inflate(inflater, parent, false)
        return AuthorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AuthorViewHolder, position: Int) {
        val author = getItem(position)
        holder.bind(author)
    }

    inner class AuthorViewHolder(private val binding: ItemAuthorBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(author: Author) {
            try {
                Log.d("PicassoImageURL", author.image)
                Picasso.get()
                    .load(author.image)
                    .into(binding.imageAuthorA)
                binding.nameAuthorA.text = author.name
                binding.numberBook.text = author.numberOfBooks.toString()
                binding.simpleRatingBar.rating=author.rating.toFloat()
                binding.root.setOnClickListener { onItemClick(author) }

            } catch (e: Exception) {
                Log.e("PicassoError", e.message ?: "Unknown error occurred")
            }
        }
    }

    class AuthorDiffCallback : DiffUtil.ItemCallback<Author>() {
        override fun areItemsTheSame(oldItem: Author, newItem: Author): Boolean {
            return oldItem.name == newItem.name // assuming 'name' is unique for each author
        }

        override fun areContentsTheSame(oldItem: Author, newItem: Author): Boolean {
            return oldItem == newItem
        }
    }
}