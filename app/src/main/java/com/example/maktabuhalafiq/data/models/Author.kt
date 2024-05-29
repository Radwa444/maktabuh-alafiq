package com.example.maktabuhalafiq.data.models

import Book

data class  Author(
    val name: String,
    val rating: Double,
    val numberOfBooks: String,
    val image: String,
    val descriptionBook: String,
    val descriptionAuthor: String,
    val books: List<Book>
){
    constructor():this("",3.5,"","","","",emptyList())
}
