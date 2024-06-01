package com.example.maktabuhalafiq.ui.Adapter

import Book
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.maktabuhalafiq.databinding.ItemFavoriteBinding
import com.squareup.picasso.Picasso

class FavoriteBooksAdapter(private var books: List<Book>) : RecyclerView.Adapter<FavoriteBooksAdapter.FavoriteBooksViewHolder>() {

    fun updateBooks(newBooks: List<Book>) {
        books = newBooks
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteBooksViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFavoriteBinding.inflate(inflater, parent, false)
        return FavoriteBooksViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteBooksViewHolder, position: Int) {
        val book = books[position]
        holder.bind(book)
    }

    override fun getItemCount(): Int = books.size

    inner class FavoriteBooksViewHolder(private val binding: ItemFavoriteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(book: Book) {
            binding.titleFa.text=book.title
            Picasso.get()
                .load(book.image)
                .into(binding.imageFa)
            binding.priceFa.text=book.price.toString()
            binding.authorFa.text=book.author
            binding.ratingBar.rating=book.rating.toFloat()
            binding.buttonLike.setOnClickListener {

            }
        }
    }
}