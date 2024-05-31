package com.example.maktabuhalafiq.data.repository.cart

import Book
import com.example.maktabuhalafiq.data.models.CartItem
import com.google.firebase.database.DatabaseReference
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(private val databaseReference: DatabaseReference) : CartRepository {

    override suspend fun addToCart(userId: String, bookId: Book, quantity: Int) {
        val cartItemRef = databaseReference.child(userId).child(bookId.id.toString())
        cartItemRef.get().addOnSuccessListener { dataSnapshot ->
            val existingCartItem = dataSnapshot.getValue(CartItem::class.java)
            val newQuantity = existingCartItem?.quantity?.plus(quantity) ?: quantity
            val cartItem = CartItem(userId, bookId, newQuantity)
            cartItemRef.setValue(cartItem)
        }
    }

    override suspend fun removeFromCart(userId: String, bookId: Int) {
        val cartItemRef = databaseReference.child(userId).child(bookId.toString())
        cartItemRef.removeValue()
    }

    override suspend fun updateCartItemQuantity(userId: String, bookId: Int, quantity: Int) {
        val cartItemRef = databaseReference.child(userId).child(bookId.toString())
        cartItemRef.child("quantity").setValue(quantity)
    }
}