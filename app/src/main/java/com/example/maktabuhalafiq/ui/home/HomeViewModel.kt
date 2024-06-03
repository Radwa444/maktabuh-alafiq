package com.example.maktabuhalafiq.ui.home

import Book
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.maktabuhalafiq.data.datasource.datastore.DataStoreManager
import com.example.maktabuhalafiq.data.models.BooksDownload
import com.example.maktabuhalafiq.data.models.Categories
import com.example.maktabuhalafiq.data.repository.books.BooksRepository
import com.example.maktabuhalafiq.data.repository.catagories.CategoriesRepository
import com.example.maktabuhalafiq.data.repository.home.HomeRepository
import com.example.maktabuhalafiq.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class HomeViewModel  @Inject constructor(private val categoriesRepository: CategoriesRepository,
                                         private val homeRepository: HomeRepository,
                                         private val booksRepository: BooksRepository,
                                         private val dataStoreManager: DataStoreManager
) :
    ViewModel() {

    private val _categories = MutableLiveData<UiState<List<Categories>>>()
    private val _booksDownload=MutableLiveData<UiState<List<BooksDownload>>>()
    private val _downloadBooks = MutableLiveData<UiState<List<Book>>>()
    val downloadBooks: LiveData<UiState<List<Book>>> get() =  _downloadBooks
    val categories: LiveData<UiState<List<Categories>>>
        get() = _categories
    val booksDownload: LiveData<UiState<List<BooksDownload>>>
        get() = _booksDownload

    fun fetchCategories() {
        _categories.value = UiState.Loading

        CoroutineScope(Dispatchers.IO).launch {
            val result = categoriesRepository.getCategories()
            _categories.postValue(result)
        }
    }
    fun fetchBooksDownload() {
        _categories.value = UiState.Loading

        CoroutineScope(Dispatchers.IO).launch {
            val result = homeRepository.getBooksDownload()
            _booksDownload.postValue(result)
        }

    }
}
