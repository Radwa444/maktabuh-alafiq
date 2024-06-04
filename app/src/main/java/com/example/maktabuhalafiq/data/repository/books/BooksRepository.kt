package com.example.maktabuhalafiq.data.repository.books



import Book
import com.example.maktabuhalafiq.utils.UiState

interface BooksRepository {
    suspend fun getBookById(categoryId:Int) : UiState<Book>
    suspend fun getBook():UiState<List<Book>>
    suspend fun getBestSellingBooks(): UiState<List<Book>>
    suspend fun getMostRatedBooks(): UiState<List<Book>>
}