package com.example.maktabuhalafiq.ui.book

import Book
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.maktabuhalafiq.data.repository.books.BooksRepository
import com.example.maktabuhalafiq.data.repository.cart.CartRepository
import com.example.maktabuhalafiq.data.repository.user.UserPreferenceRepository
import com.example.maktabuhalafiq.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class BooksViewModel @Inject constructor(
    private val cartRepository: CartRepository,
    private val userRepository:UserPreferenceRepository
) : ViewModel() {
    private val _books = MutableLiveData<List<Book>>()

    val books: LiveData<List<Book>> get() = _books
    fun fetchBooks() {

        val fetchedBooks = listOf<Book>()
        _books.value = fetchedBooks
    }


    fun addToCart( book: Book, quantity: Int) {
        viewModelScope.launch {
            val userId = userRepository.getUserID().first()
            if (userId != null) {
                cartRepository.addToCart(userId, book, quantity)
            }

        }
    }
}