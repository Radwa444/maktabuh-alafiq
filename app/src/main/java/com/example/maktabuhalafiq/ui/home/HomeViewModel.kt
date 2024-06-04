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
import com.example.maktabuhalafiq.data.repository.cart.CartRepository
import com.example.maktabuhalafiq.data.repository.catagories.CategoriesRepository
import com.example.maktabuhalafiq.data.repository.home.HomeRepository
import com.example.maktabuhalafiq.data.repository.user.UserPreferenceRepository
import com.example.maktabuhalafiq.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class HomeViewModel  @Inject constructor(private val categoriesRepository: CategoriesRepository,
                                         private val homeRepository: HomeRepository,
                                         private val booksRepository: BooksRepository,
                                         private val cartRepository: CartRepository,
                                         private val userRepository: UserPreferenceRepository

) :
    ViewModel() {

    private val _categories = MutableLiveData<UiState<List<Categories>>>()
    private val _books = MutableLiveData<UiState<List<Book>>>()
    private val _booksDownload=MutableLiveData<UiState<List<BooksDownload>>>()
    private val _downloadBooks = MutableLiveData<UiState<List<Book>>>()
    private val _MostRatedBooks = MutableLiveData<UiState<List<Book>>>()
    private val _bestSellingBooks = MutableLiveData<UiState<List<Book>>>()
    val downloadBooks: LiveData<UiState<List<Book>>> get() =  _downloadBooks
    val categories: LiveData<UiState<List<Categories>>>
        get() = _categories
    val books: LiveData<UiState<List<Book>>>
        get() = _books
    val booksDownload: LiveData<UiState<List<BooksDownload>>>
        get() = _booksDownload
    val mostRatedBooks: LiveData<UiState<List<Book>>>
        get() =_MostRatedBooks
    val bestSellingBooks: LiveData<UiState<List<Book>>>
        get() = _bestSellingBooks
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
    fun addToCart( book: Book, quantity: Int) {
        viewModelScope.launch {
            val userId = userRepository.getUserID().first()
            if (userId != null) {
                cartRepository.addToCart(userId, book, quantity)
            }

        }
    }
    fun fetchBestSellingBooks() {
        _bestSellingBooks.value = UiState.Loading
        viewModelScope.launch {
            val result = booksRepository.getBestSellingBooks()
            _bestSellingBooks.postValue(result)
        }
    }
    fun fetchMostRatedBooks() {
        _MostRatedBooks.value = UiState.Loading
        viewModelScope.launch {
            val result = booksRepository.getMostRatedBooks()
            _MostRatedBooks.postValue(result)
        }
    }
}
