package com.example.maktabuhalafiq.data.repository.auth

import com.example.maktabuhalafiq.data.models.User
import com.example.maktabuhalafiq.utils.UiState

interface AuthRepository {
    suspend fun signup(user:User,result: (UiState<String>) -> Unit)

}