package com.example.maktabuhalafiq.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.maktabuhalafiq.data.models.BooksDownload
import com.example.maktabuhalafiq.data.models.Categories
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
                                         private val homeRepository: HomeRepository
) :
    ViewModel() {

    private val _categories = MutableLiveData<UiState<List<Categories>>>()
    private val _booksDownload=MutableLiveData<UiState<List<BooksDownload>>>()
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
