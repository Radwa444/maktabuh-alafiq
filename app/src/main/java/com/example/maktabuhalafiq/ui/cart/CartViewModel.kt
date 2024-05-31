package com.example.maktabuhalafiq.ui.cart

import Book
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.maktabuhalafiq.data.models.CartItem
import com.example.maktabuhalafiq.data.repository.cart.CartRepository
import com.example.maktabuhalafiq.utils.UiState
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val cartRepository: CartRepository) : ViewModel() {

    private val _cartBooks = MutableLiveData<List<CartItem>>()
    val cartBooks: LiveData<List<CartItem>> get() = _cartBooks
    private val _totalPrice = MutableLiveData<String>()
    val totalPrice: LiveData<String> get() = _totalPrice
    private val  _totalPriceSummation  = MutableLiveData<Double>()
    val totalPriceSummation: LiveData<Double> get() = _totalPriceSummation

    fun fetchCartBooks(userId: String) {
        val cartItemRef = FirebaseDatabase.getInstance().getReference("CartItem").child(userId)
        cartItemRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val books = mutableListOf<CartItem>()

                for (data in snapshot.children) {
                    val cartItem = data.getValue(CartItem::class.java)

                    if (cartItem != null) {
                        books.add(cartItem)

                    }
                }
                _cartBooks.value = books
                updateTotalPrice(books)
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })
    }

    suspend fun updateCartItemQuantity(userId: String, bookId: Int, quantity: Int) {
        cartRepository.updateCartItemQuantity(userId, bookId, quantity)
    }
    private fun updateTotalPrice(books: List<CartItem>) {
        var total = 0.0
        for (cartItem in books) {
            total += cartItem.books.price*cartItem.quantity

        }
        Log.e("Tag",total.toString())
        _totalPrice.value = total.toString()
        _totalPriceSummation.value=total + 40
    }
}