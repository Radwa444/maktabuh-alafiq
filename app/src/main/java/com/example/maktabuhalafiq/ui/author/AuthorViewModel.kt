package com.example.maktabuhalafiq.ui.author

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.maktabuhalafiq.data.models.Author
import com.example.maktabuhalafiq.data.repository.author.AuthorRepository
import com.example.maktabuhalafiq.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthorViewModel @Inject constructor(private val authorRepository: AuthorRepository) : ViewModel() {

    private val _author = MutableLiveData<UiState<List<Author>>>()
    val author: LiveData<UiState<List<Author>>> get() = _author

    fun fetchAuthors() {
        _author.value = UiState.Loading

        CoroutineScope(Dispatchers.IO).launch {
            val result = authorRepository.getAuthor()
            _author.postValue(result)
        }
    }
}