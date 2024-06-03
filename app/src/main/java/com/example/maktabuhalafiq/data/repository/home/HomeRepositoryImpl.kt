package com.example.maktabuhalafiq.data.repository.home

import android.util.Log
import com.example.maktabuhalafiq.data.models.BooksDownload
import com.example.maktabuhalafiq.utils.UiState
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val databaseReference: DatabaseReference):HomeRepository {
    override suspend fun getBooksDownload(): UiState<List<BooksDownload>> {
        return try {
            val booksDownloadList = mutableListOf<BooksDownload>()
            val snapshat = databaseReference.get().await()
            for (booksDownloadSnapshot in snapshat.children) {
                val category = booksDownloadSnapshot.getValue(BooksDownload::class.java)
                category?.let {
                    booksDownloadList.add(it)
                    Log.e("TAG", it.toString())
                }
            }
            UiState.Success(booksDownloadList)


        } catch (e: Exception) {
            Log.e("TAGError", UiState.Failure(e.message).toString())
            UiState.Failure(e.message)

        }
    }

    override suspend fun getBookById(id: Int): UiState<BooksDownload> {
        return try {
            val bookSnapshot = databaseReference.child(id.toString()).get().await()
            val book = bookSnapshot.getValue(BooksDownload::class.java)
            if (book != null) {
                UiState.Success(book)
            } else {
                UiState.Failure("Book not found")
            }
        } catch (e: Exception) {
            Log.e("BooksRepository", "Failed to fetch book by ID $id: ${e.message}")
            UiState.Failure(e.message ?: "Unknown error occurred")
        }
    }
}