package com.example.maktabuhalafiq.ui.auth.viewModels

import androidx.lifecycle.ViewModel
import com.example.maktabuhalafiq.data.repository.user.UserPreferenceRepository

class LoginViewModel(
    val userPrefs:UserPreferenceRepository
) : ViewModel(){

}