package com.example.maktabuhalafiq.ui.download

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.maktabuhalafiq.data.datasource.datastore.DataStoreManager
import com.example.maktabuhalafiq.data.models.BooksDownload
import com.example.maktabuhalafiq.data.repository.home.HomeRepository
import com.example.maktabuhalafiq.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class DownloadViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {
    private val _downloadBooks = MutableLiveData<UiState<List<BooksDownload>>>()
    val downloadBooks: LiveData<UiState<List<BooksDownload>>> get() = _downloadBooks
    init {
        observeDownloadBooks()
    }

    private fun observeDownloadBooks() {
        viewModelScope.launch {
            dataStoreManager.downloadBooksFlow.collect { ids ->
                getDownloadBooksByIds(ids)
            }
        }
    }

    private fun getDownloadBooksByIds(ids: Set<String>) {
        _downloadBooks.value = UiState.Loading
        viewModelScope.launch {
            try {
                val books = ids.mapNotNull { id ->
                    val result = homeRepository.getBookById(id.toInt())
                    if (result is UiState.Success) result.data else null
                }
                _downloadBooks.value = UiState.Success(books)
                Log.e("downloadBooksViewModel",books.toString())
            } catch (e: Exception) {
                Log.e("downloadBooksViewModel",e.toString())
                _downloadBooks.value = UiState.Failure(e.message ?: "Unknown error")
            }
        }
    }
}