package com.example.maktabuhalafiq.data.models

import Book

data class CartItem(
    val userId: String,
    val books: List<Book>,
    val quantity: Int)
