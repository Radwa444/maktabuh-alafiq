package com.example.maktabuhalafiq.data.repository.catagories


import com.example.maktabuhalafiq.data.models.Categories
import com.example.maktabuhalafiq.utils.UiState

interface CategoriesRepository{
    suspend fun getCategories():UiState<List<Categories>>

}