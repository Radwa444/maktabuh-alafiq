package com.example.maktabuhalafiq.ui.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.maktabuhalafiq.data.models.Categories
import com.example.maktabuhalafiq.data.repository.catagories.CategoriesRepository
import com.example.maktabuhalafiq.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val categoriesRepository: CategoriesRepository) :
    ViewModel() {

    private val _categories = MutableLiveData<UiState<List<Categories>>>()
    val categories: LiveData<UiState<List<Categories>>>
        get() = _categories

    fun fetchCategories() {
        _categories.value = UiState.Loading

        CoroutineScope(Dispatchers.IO).launch {
            val result = categoriesRepository.getCategories()
            _categories.postValue(result)
        }
    }
}
