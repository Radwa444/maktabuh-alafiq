package com.example.maktabuhalafiq.data.repository.author

import com.example.maktabuhalafiq.data.models.Author
import com.example.maktabuhalafiq.utils.UiState

interface AuthorRepository {
    suspend fun getAuthor(): UiState<List<Author>>
}