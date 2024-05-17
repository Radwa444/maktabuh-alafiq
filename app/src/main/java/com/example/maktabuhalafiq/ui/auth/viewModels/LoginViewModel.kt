package com.example.maktabuhalafiq.ui.auth.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.maktabuhalafiq.data.repository.auth.AuthRepositoryImpl
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
class LoginViewModel @Inject constructor(
   private val authRepositoryImpl: AuthRepositoryImpl
) : ViewModel(){
     val loginState:MutableStateFlow<UiState<String>?> = MutableStateFlow(null)
     val email=MutableStateFlow("")
     val password=MutableStateFlow("")
    val isCheckboxChecked = MutableStateFlow(false)
    val loginValid:Flow<Boolean> = combine(email,password,isCheckboxChecked){
        email , password ,isCheck ->
        email.isValidEmail()&&password.length >= 6 && isCheck
    }
fun login(){

    viewModelScope.launch {
        val email=email.value
        val password=password.value
        if (loginValid.first()){
            authRepositoryImpl.login(email,password).onEach {
                when(it){
                    is UiState.Loading ->{
                      loginState.update {
                          UiState.Loading
                      }
                    }
                    is  UiState.Success -> {
                        loginState.update {
                            UiState.Success("login successfully")
                        }


                    }
                    is UiState.Failure -> {
                    loginState.value=UiState.Failure(it.error?:("Error Login"))
                    }
                }

            }.launchIn(viewModelScope)
        } else{
            loginState.update { UiState.Failure(("invalid email and password")) }
        }

    }
}
companion object{
    private const val TAG="LoginViewModel"
}
}
//class LoginViewModelFactory(private val userPrefs:UserPreferenceRepository,private val authRepositoryImpl: AuthRepositoryImpl):ViewModelProvider.Factory{
//    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
//        if (modelClass.isAssignableFrom(UserViewModel::class.java)){
//            @Suppress("UNCHECKED_CAST") return LoginViewModel(userPrefs,authRepositoryImpl ) as T
//        }
//        throw IllegalArgumentException("unknown viewmodel class")
//    }
//}