package com.example.maktabuhalafiq.data.repository.cart

import Book

interface CartRepository {
    suspend fun addToCart(userId: String, bookId: Book, quantity: Int)
    suspend fun removeFromCart(userId: String, bookId: Int)
    suspend fun updateCartItemQuantity(userId: String, bookId: Int, quantity: Int)
}