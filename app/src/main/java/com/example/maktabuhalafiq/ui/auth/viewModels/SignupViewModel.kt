package com.example.maktabuhalafiq.ui.auth.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.maktabuhalafiq.data.models.User
import com.example.maktabuhalafiq.data.repository.auth.AuthRepositoryImpl
import com.example.maktabuhalafiq.data.repository.user.UserPreferenceRepository
import com.example.maktabuhalafiq.utils.UiState
import com.example.maktabuhalafiq.utils.isValidEmail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class SignupViewModel @Inject constructor(
    private val authRepositoryImpl: AuthRepositoryImpl,
    private val userPreferenceRepository: UserPreferenceRepository
) : ViewModel(){
    val signupState: MutableStateFlow<UiState<String>?> = MutableStateFlow(null)
    val otpState: MutableStateFlow<UiState<Boolean>?> = MutableStateFlow(null)
    val name=MutableStateFlow("")
    val email= MutableStateFlow("")
    val password= MutableStateFlow("")
    val confirmPassword=MutableStateFlow("")
    val otp = MutableStateFlow("")
    val signupValid: Flow<Boolean> = combine(name,email,password,confirmPassword){
            name ,email , password , confirmPassword ->
        email.isValidEmail() && password.length >= 6 && password==confirmPassword
    }
    fun signup(){

        viewModelScope.launch {
            val email=email.value
            val password=password.value
            if (signupValid.first()) {
                authRepositoryImpl.signup(name.toString(), email, password).onEach {
                    when (it) {
                        is UiState.Loading -> {
                            signupState.update { UiState.Loading }
                        }
                        is UiState.Success -> {
                            signupState.update { UiState.Success("Signup successful") }
                            userPreferenceRepository.saveLoginState(true)
                            userPreferenceRepository.saveUserID(it.data.id)
                        }
                        is UiState.Failure -> {
                            signupState.value = UiState.Failure(it.error ?: "Error Signup")
                        }
                    }
                }.launchIn(viewModelScope)
            } else {
                signupState.update { UiState.Failure("Invalid email and password") }
            }

        }
    }
    fun verifyOtp() {
        viewModelScope.launch {
            authRepositoryImpl.verifyOtp(email.value, otp.value).onEach {
                when (it) {
                    is UiState.Loading -> {
                        otpState.update { UiState.Loading }
                    }
                    is UiState.Success -> {
                        otpState.update { UiState.Success(true) }
                    }
                    is UiState.Failure -> {
                        Log.d("verifyOtp", it.error.toString())
                        otpState.value = UiState.Failure(it.error ?: "Error during OTP verification")
                    }
                }
            }.launchIn(viewModelScope)
        }
    }



}