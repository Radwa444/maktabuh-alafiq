package com.example.maktabuhalafiq.ui.favoriteBooks

import Book
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.maktabuhalafiq.data.datasource.datastore.DataStoreManager
import com.example.maktabuhalafiq.data.repository.books.BooksRepository
import com.example.maktabuhalafiq.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteBooksViewModel @Inject constructor(
    private val booksRepository: BooksRepository,
    private val dataStoreManager: DataStoreManager // Inject the DataStoreManager
) : ViewModel() {
    private val _favoriteBooks = MutableLiveData<UiState<List<Book>>>()
    val favoriteBooks: LiveData<UiState<List<Book>>> get() = _favoriteBooks

    init {
        observeFavoriteBooks()
    }

    private fun observeFavoriteBooks() {
        viewModelScope.launch {
            dataStoreManager.favoriteBooksFlow.collect { ids ->
                getFavoriteBooksByIds(ids)
            }
        }
    }

    fun getFavoriteBooksByIds(ids: Set<String>) {
        _favoriteBooks.value = UiState.Loading
        viewModelScope.launch {
            try {
                val books = ids.mapNotNull { id ->
                    val result = booksRepository.getBookById(id.toInt())
                    if (result is UiState.Success) result.data else null
                }
                _favoriteBooks.value = UiState.Success(books)
            } catch (e: Exception) {
                _favoriteBooks.value = UiState.Failure(e.message ?: "Unknown error")
            }
        }
    }
}