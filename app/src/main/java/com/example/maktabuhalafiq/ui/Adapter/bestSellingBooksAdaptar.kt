package com.example.maktabuhalafiq.ui.Adapter

import Book
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.maktabuhalafiq.databinding.ItemProductBinding
import com.squareup.picasso.Picasso

class bestSellingBooksAdaptar (
    private var bookList: List<Book>,
    private val onItemClick: (Book) -> Unit,
    private val addCart: (Book) -> Unit,
) : RecyclerView.Adapter<bestSellingBooksAdaptar.BookViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = bookList[position]
        holder.bind(book)
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    fun updateBooks(newBooks: List<Book>) {
        bookList = newBooks
        notifyDataSetChanged()
    }

    inner class BookViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(book: Book) {
            binding.apply {
                Picasso.get()
                    .load(book.image)
                    .into(imageItem)
                nameProduct.text = book.title
                price.text = book.price.toString()
                ratingBar.rating=book.rating.toFloat()
                root.setOnClickListener { onItemClick(book) }
                iconButton.setOnClickListener {
                    addCart(book)
                }
            }
        }
    }
}
