package com.example.maktabuhalafiq.ui.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.maktabuhalafiq.data.models.BooksDownload
import com.example.maktabuhalafiq.databinding.ItemDownloadBinding
import com.squareup.picasso.Picasso

class DoneDownloadAdapter(private var books: List<BooksDownload>) : RecyclerView.Adapter<DoneDownloadAdapter.BookViewHolder>() {

    inner class BookViewHolder(private val binding: ItemDownloadBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(book: BooksDownload) {
            binding.titleDo.text = book.title
            binding.authorDo.text = book.author // Assuming you have a TextView for author
            Picasso.get()
                .load(book.coverImageUrl)
                .into(binding.imageDo)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding = ItemDownloadBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(books[position])
    }

    override fun getItemCount(): Int = books.size

    fun updateBooks(newBooks: List<BooksDownload>) {
        books = newBooks
        notifyDataSetChanged()
    }
}
