package com.example.maktabuhalafiq.ui.auth.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras

import com.example.maktabuhalafiq.data.repository.user.UserPreferenceRepositoryImpl
import java.lang.IllegalArgumentException

class UserViewModel(
    private val userPreferenceRepository: UserPreferenceRepositoryImpl
):ViewModel() {
    suspend fun isUserLoggedIn()=userPreferenceRepository.isUserLoggedIn()
}
class UserViewModelFactory(private val userPreferenceRepositoryImpl: UserPreferenceRepositoryImpl):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)){
            @Suppress("UNCHECKED_CAST") return UserViewModel(userPreferenceRepositoryImpl ) as T
        }
        throw IllegalArgumentException("uNKNOWN vIEWMODEL CLASS")
    }
}