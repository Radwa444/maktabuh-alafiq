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
    override suspend fun getBooks(categoryId: Int): UiState<List<Book>> {
        return try {
            val booksList = mutableListOf<Book>()
            val booksSnapshot = databaseReference.child("Categories").child(categoryId.toString()).child("books").get().await()
            for (bookSnapshot in booksSnapshot.children) {
                val book = bookSnapshot.getValue(Book::class.java)
                book?.let {
                    booksList.add(it)
                    Log.e("BooksRepository", it.toString())
                }
            }

            UiState.Success(booksList)
        } catch (e: Exception) {
            Log.e("BooksRepository", "Failed to fetch books for category $categoryId: ${e.message}")
            UiState.Failure(e.message ?: "Unknown error occurred")
        }
    }
}

//class CategoriesRepositoryImpl @Inject constructor(private val databaseReference: DatabaseReference) : CategoriesRepository {
//    override suspend fun getCategoriesById(categoryId: Int): UiState<List<Category>> {
//        return try {
//            val categoryList = mutableListOf<Category>()
//
//            // Retrieve the specified category by categoryId
//            val categorySnapshot = databaseReference.child("Categories").child(categoryId.toString()).get().await()
//            val category = categorySnapshot.getValue(Category::class.java)
//
//            category?.let { cat ->
//                // If the category exists, retrieve its books
//                val booksSnapshot = databaseReference.child("Categories").child(categoryId.toString()).child("books").get().await()
//
//                val books = mutableListOf<Book>()
//                for (bookSnapshot in booksSnapshot.children) {
//                    val book = bookSnapshot.getValue(Book::class.java)
//                    book?.let {
//                        books.add(it)
//                    }
//                }
//
//                // Add the retrieved category with its books to the list
//                val categoryWithBooks = cat.copy(books = books)
//                categoryList.add(categoryWithBooks)
//            }
//
//            UiState.Success(categoryList)
//        } catch (e: Exception) {
//            Log.e("TAGError", "Failed to fetch category with books for id $categoryId: ${e.message}")
//            UiState.Failure(e.message ?: "Unknown error occurred")
//        }
//    }
//}
