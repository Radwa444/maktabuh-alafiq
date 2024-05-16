package com.example.maktabuhalafiq.data.repository.auth
import com.example.maktabuhalafiq.utils.UiState
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl  @Inject constructor(private val auth: FirebaseAuth):AuthRepository {
    override suspend fun login(email: String, password: String): Flow<UiState<String>> =flow{
        try {
            emit(UiState.Loading)
            val resultAuth=auth.signInWithEmailAndPassword(email,password).await()
            resultAuth.user?.let {
                emit(UiState.Success(it.uid))
            }?:run {
                emit(UiState.Failure(Exception("User not Found").toString()))
            }
        }catch (e:Exception){
           emit(UiState.Failure(e.toString()))
        }
    }
    companion object{
        private const val TAG="AuthRepositoryImpl"
    }
}