package com.example.maktabuhalafiq.data.models

import Book

data class CartItem(
    val userId: String,
    val books: Book,
    val quantity: Int)
{
    constructor() : this("", Book(), 0)
}
