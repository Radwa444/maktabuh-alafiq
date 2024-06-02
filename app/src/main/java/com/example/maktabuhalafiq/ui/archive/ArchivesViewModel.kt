package com.example.maktabuhalafiq.ui.archive

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
class ArchivesViewModel @Inject constructor(
    private val booksRepository: BooksRepository,
    private val dataStoreManager: DataStoreManager // Inject the DataStoreManager
) : ViewModel() {
    private val _archivesBooks = MutableLiveData<UiState<List<Book>>>()
    val archivesBooks: LiveData<UiState<List<Book>>> get() = _archivesBooks

    init {
        observeFavoriteBooks()
    }

    private fun observeFavoriteBooks() {
        viewModelScope.launch {
            dataStoreManager.archivesBooksFlow.collect { ids ->
                getArchivesBooksByIds(ids)
            }
        }
    }

    fun getArchivesBooksByIds(ids: Set<String>) {
        _archivesBooks.value = UiState.Loading
        viewModelScope.launch {
            try {
                val books = ids.mapNotNull { id ->
                    val result = booksRepository.getBookById(id.toInt())
                    if (result is UiState.Success) result.data else null
                }
                _archivesBooks.value = UiState.Success(books)
            } catch (e: Exception) {
                _archivesBooks.value = UiState.Failure(e.message ?: "Unknown error")
            }
        }
    }
}