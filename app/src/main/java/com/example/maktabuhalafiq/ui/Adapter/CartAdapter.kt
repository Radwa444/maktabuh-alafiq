package com.example.maktabuhalafiq.ui.Adapter

import Book
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.maktabuhalafiq.databinding.ProdectCartBinding
import com.squareup.picasso.Picasso

class CartAdapter(private val cartItems: List<Book>) :
    RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    class CartViewHolder(private val binding: ProdectCartBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(book: Book) {
            try {
                Log.d("PicassoImageURL", book.image)
                Picasso.get()
                    .load(book.image)
                    .into(binding.imageBookCart)
                binding.nameCart.text = book.title
                binding.auhtorCart.text = book.author
                binding.pirceCart.text = book.price.toString()
                binding.btnDecreaseQuantity.setOnClickListener {
                    if (binding.tvQuantity.text.toString().toInt() > 1) {
                        var decreas = binding.tvQuantity.text.toString().toInt()
                        decreas--
                        binding.tvQuantity.text = decreas.toString()
                    }
                }
                binding.btnIncreaseQuantity.setOnClickListener {

                    var increaseQuantity = binding.tvQuantity.text.toString().toInt()
                    increaseQuantity++
                    binding.tvQuantity.text = increaseQuantity.toString()

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
        val author = cartItems[position]
        holder.bind(author)
    }

    override fun getItemCount() = cartItems.size
}