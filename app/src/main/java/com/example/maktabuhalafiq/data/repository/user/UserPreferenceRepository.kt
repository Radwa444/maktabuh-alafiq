package com.example.maktabuhalafiq.data.repository.user

import com.example.maktabuhalafiq.utils.UiState
import kotlinx.coroutines.flow.Flow

interface UserPreferenceRepository {
   suspend fun isUserLoggedIn(): Flow<Boolean>
   suspend fun saveLoginState(isLoggedIn:Boolean)
   suspend fun saveUserID(userID: String)
}