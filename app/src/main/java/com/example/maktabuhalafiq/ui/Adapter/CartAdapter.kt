package com.example.maktabuhalafiq.ui.Adapter

import Book
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.maktabuhalafiq.data.models.CartItem
import com.example.maktabuhalafiq.databinding.ProdectCartBinding
import com.squareup.picasso.Picasso

class CartAdapter(
    private var cartItems: List<CartItem>,
    private val quantityChangeListener: (Book, Int) -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    class CartViewHolder(private val binding: ProdectCartBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(book: CartItem, listener: (Book, Int) -> Unit) {
            try {
                Log.d("PicassoImageURL", book.books.image)
                Picasso.get()
                    .load(book.books.image)
                    .into(binding.imageBookCart)
                binding.nameCart.text = book.books.title
                binding.auhtorCart.text =book.books.author
                binding.pirceCart.text = book.books.price.toString()
                binding.tvQuantity.text = book.quantity.toString()
                binding.tvQuantity.text = book.quantity.toString()
                binding.btnDecreaseQuantity.setOnClickListener {
                    var quantity = binding.tvQuantity.text.toString().toInt()
                    if (quantity > 1) {
                        quantity--

                        listener(book.books, quantity)
                    }
                }

                binding.btnIncreaseQuantity.setOnClickListener {
                    var quantity = binding.tvQuantity.text.toString().toInt()
                    quantity++
                    listener(book.books, quantity)
                }


            } catch (e: Exception) {
                Log.e("PicassoError", e.message ?: "Unknown error occurred")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.CartViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ProdectCartBinding.inflate(inflater, parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val book = cartItems[position]
        holder.bind(book, quantityChangeListener)
    }

    override fun getItemCount() = cartItems.size

    fun updateBooks(newCartItems: List<CartItem>) {
        cartItems = newCartItems
        notifyDataSetChanged()
    }
}