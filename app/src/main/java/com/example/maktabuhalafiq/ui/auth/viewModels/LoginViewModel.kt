package com.example.maktabuhalafiq.ui.auth.viewModels

import androidx.lifecycle.ViewModel
import com.example.maktabuhalafiq.data.repository.user.UserPreferenceRepository
import kotlinx.coroutines.flow.Flow

class LoginViewModel(
    val userPrefs:UserPreferenceRepository
) : ViewModel(){
suspend fun isUserLoggedIn(): Flow<Boolean> =userPrefs.isUserLoggedIn()
}