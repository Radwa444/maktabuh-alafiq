package com.example.maktabuhalafiq.ui.Adapter

import Book
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.maktabuhalafiq.databinding.ItemArchivesBinding
import com.example.maktabuhalafiq.databinding.ItemFavoriteBinding
import com.squareup.picasso.Picasso

class BooksArchivesAdapter (private var books: List<Book>) : RecyclerView.Adapter<BooksArchivesAdapter.BookViewHolder>() {

    inner class BookViewHolder(private val binding: ItemArchivesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(book: Book) {
            binding.titleAr.text = book.title
            binding.authorAr.text = book.author
            binding.ratingBar.rating=book.rating.toFloat()
            binding.priceAr.text=book.price.toString()
            Picasso.get()
                .load(book.image)
                .into(binding.imageAr)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding = ItemArchivesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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



