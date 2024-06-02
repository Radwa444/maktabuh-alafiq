package com.example.maktabuhalafiq.ui.Adapter

import Book
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.maktabuhalafiq.R
import com.example.maktabuhalafiq.databinding.ItemFavoriteBinding
import com.squareup.picasso.Picasso

class FavoriteBooksAdapter(private var books: List<Book>) : RecyclerView.Adapter<FavoriteBooksAdapter.BookViewHolder>() {

    inner class BookViewHolder(private val binding: ItemFavoriteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(book: Book) {
            binding.titleFa.text = book.title
            binding.authorFa.text = book.author
            binding.ratingBar.rating=book.rating.toFloat()
            binding.priceFa.text=book.price.toString()
            Picasso.get()
                .load(book.image)
                .into(binding.imageFa)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding = ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(books[position])
    }

    override fun getItemCount(): Int = books.size

    fun updateBooks(newBooks: List<Book>) {
        books = newBooks
        notifyDataSetChanged()
    }
}



