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

    override suspend fun getBook(): UiState<List<Book>> {
        return try {
            val booksList= mutableListOf<Book>()
            val snapshat=databaseReference.get().await()
            for(bookSnapshot in snapshat.children){
                val books = bookSnapshot.getValue(Book::class.java)
               books?.let {
                   booksList.add(it)
                    Log.e("TAG",it.toString())
                }

            }
            UiState.Success(booksList)
        }catch (e:Exception){
            Log.e("BooksError",UiState.Failure(e.message).toString())
            UiState.Failure(e.message)
        }
    }

    override suspend fun getBestSellingBooks(): UiState<List<Book>> {
        return try {
            val booksList = mutableListOf<Book>()
            val snapshot = databaseReference.get().await()
            for (bookSnapshot in snapshot.children) {
                val book = bookSnapshot.getValue(Book::class.java)
                book?.let {
                    booksList.add(it)
                }
            }
            val sortedBooks = booksList.sortedByDescending { it.sales }.take(10)
            UiState.Success(sortedBooks)
        } catch (e: Exception) {
            Log.e("BooksRepository", "Failed to fetch best-selling books: ${e.message}")
            UiState.Failure(e.message ?: "Unknown error occurred")
        }
    }
    override suspend fun getMostRatedBooks(): UiState<List<Book>> {
        return try {
            val booksList = mutableListOf<Book>()
            val snapshot = databaseReference.get().await()
            for (bookSnapshot in snapshot.children) {
                val book = bookSnapshot.getValue(Book::class.java)
                book?.let {
                    booksList.add(it)
                }
            }
            val sortedBooks = booksList.sortedByDescending { it.rating }.take(10)
            UiState.Success(sortedBooks)
        } catch (e: Exception) {
            Log.e("BooksRepository", "Failed to fetch best-selling books: ${e.message}")
            UiState.Failure(e.message ?: "Unknown error occurred")
        }
    }
    }


