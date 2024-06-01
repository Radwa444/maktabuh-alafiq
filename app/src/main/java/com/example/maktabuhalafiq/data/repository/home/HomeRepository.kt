package com.example.maktabuhalafiq.data.repository.home

import com.example.maktabuhalafiq.data.models.BooksDownload
import com.example.maktabuhalafiq.utils.UiState

interface HomeRepository {
    suspend fun getBooksDownload(): UiState<List<BooksDownload>>
}