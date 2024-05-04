package com.example.maktabuhalafiq.data.models

import Book

data class Categories(
    val id: Int,
    val name: String,
    val image: String,
    val books: List<Book>
) {

    constructor() : this(0, "", "", emptyList())
}
