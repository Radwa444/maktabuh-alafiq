package com.example.maktabuhalafiq.data.repository.books

import Book
import android.util.Log

import com.example.maktabuhalafiq.data.models.Categories

import com.example.maktabuhalafiq.utils.UiState
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.getValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class BooksRepositoryImpl(private val databaseReference: DatabaseReference) : BooksRepository {
    override suspend fun getBookById(bookId: Int): UiState<Book> {
        return try {
            val bookSnapshot = databaseReference.child(bookId.toString()).get().await()
            val book = bookSnapshot.getValue(Book::class.java)
            if (book != null) {
                UiState.Success(book)
            } else {
                UiState.Failure("Book not found")
            }
        } catch (e: Exception) {
            Log.e("BooksRepository", "Failed to fetch book by ID $bookId: ${e.message}")
            UiState.Failure(e.message ?: "Unknown error occurred")
        }
    }
}

