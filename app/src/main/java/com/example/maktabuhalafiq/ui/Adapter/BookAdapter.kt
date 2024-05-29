package com.example.maktabuhalafiq.ui.Adapter

import Book
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.maktabuhalafiq.databinding.ItemBookBinding
import com.squareup.picasso.Picasso
class BookAdapter(
    private var bookList: List<Book>,
    private val onItemClick: (Book) -> Unit
) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding = ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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

    inner class BookViewHolder(private val binding: ItemBookBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(book: Book) {
            binding.apply {
                Picasso.get()
                    .load(book.image)
                    .into(imageBook)

                buttomPrice.text = book.price.toString()
                title.text = book.title
                textAuthor.text = book.author
                simpleRatingBar.rating=book.rating.toFloat()
                root.setOnClickListener { onItemClick(book) }
            }
        }
    }
}
