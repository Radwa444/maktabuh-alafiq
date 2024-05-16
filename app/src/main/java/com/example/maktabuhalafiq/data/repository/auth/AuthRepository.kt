package com.example.maktabuhalafiq.data.repository.auth

import com.example.maktabuhalafiq.data.models.User
import com.example.maktabuhalafiq.utils.UiState
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    suspend fun login(email:String,password:String):Flow<UiState<String>>

}