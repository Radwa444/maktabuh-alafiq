package com.example.maktabuhalafiq.data.repository.books



import Book
import com.example.maktabuhalafiq.utils.UiState

interface BooksRepository {
    suspend fun getBookById(categoryId:Int) : UiState<Book>
}