package com.example.maktabuhalafiq.data.repository.cart

import Book
import com.example.maktabuhalafiq.data.models.CartItem
import com.google.firebase.database.DatabaseReference
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(private val databaseReference: DatabaseReference) : CartRepository {

    override suspend fun addToCart(userId: String, bookId: List<Book>, quantity: Int) {
        val cartItemRef = databaseReference.child(userId)
        val cartItem = CartItem(userId, bookId, quantity)
        cartItemRef.setValue(cartItem)
    }

    override suspend fun removeFromCart(userId: String, bookId: Int) {
        val cartItemRef = databaseReference.child(userId)
        cartItemRef.removeValue()
    }

    override suspend fun updateCartItemQuantity(userId: String, bookId: Int, quantity: Int) {
        val cartItemRef = databaseReference.child(userId)
        cartItemRef.child("quantity").setValue(quantity)
    }
}