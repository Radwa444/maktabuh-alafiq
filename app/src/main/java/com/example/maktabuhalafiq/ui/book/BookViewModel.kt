package com.example.maktabuhalafiq.ui.book

import Book
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.maktabuhalafiq.data.repository.books.BooksRepository
import com.example.maktabuhalafiq.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class BookViewModel @Inject constructor(private val repository: BooksRepository) : ViewModel(){
    private val _books = MutableLiveData<UiState<List<Book>>>()
    val books: LiveData<UiState<List<Book>>> get() = _books
    fun fetchBooks(idCategory: Int) {
        _books.value = UiState.Loading

        CoroutineScope(Dispatchers.IO).launch {
            try {

            }catch (e:Exception){
                Log.e("BookViewModel", "Error loading books for category $idCategory", e)
            }

        }
    }
}

