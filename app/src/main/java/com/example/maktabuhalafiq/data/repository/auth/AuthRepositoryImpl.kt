package com.example.maktabuhalafiq.data.repository.auth
import android.util.Log
import com.example.maktabuhalafiq.data.models.User
import com.example.maktabuhalafiq.utils.UiState
import com.google.firebase.auth.ActionCodeSettings
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl  @Inject constructor(private val auth: FirebaseAuth,private val databaseReference: DatabaseReference):AuthRepository {
    override suspend fun login(email: String, password: String): Flow<UiState<User>> = flow {
        try {
            emit(UiState.Loading)
            val resultAuth = auth.signInWithEmailAndPassword(email, password).await()
            resultAuth.user?.let {
                val user = User(
                    id = it.uid,
                    name = "",
                    email = email
                )
                emit(UiState.Success(user))
            } ?: run {
                emit(UiState.Failure(Exception("User not Found").toString()))
            }
        } catch (e: Exception) {
            emit(UiState.Failure(e.toString()))
        }
    }

    override suspend fun signup(
        name: String,
        email: String,
        password: String
    ): Flow<UiState<User>> = flow {
        try {
            emit(UiState.Loading)
            val resultAuth = auth.createUserWithEmailAndPassword(email, password).await()
            resultAuth.user?.let {
                val user = User(
                    id = it.uid,
                    name = name,
                    email = email
                )
                Log.d(TAG, "createUserWithEmail:success")
                databaseReference.child("User").child(it.uid).setValue(user).await()
                emit(UiState.Success(user))
                sendOtp(email)
            } ?: run {
                emit(UiState.Failure(Exception("don't signup").toString()))
            }
        } catch (e: Exception) {
            Log.w(TAG, "createUserWithEmail:failure", e)
            emit(UiState.Failure(e.toString()))
        }

    }

    override suspend fun sendOtp(email: String): Flow<UiState<String>> = flow {
        try {
            emit(UiState.Loading)
            val actionCodeSettings = ActionCodeSettings.newBuilder()
                .setUrl("https://www.example.com/verify?uid=${auth.currentUser?.uid}")
                .setHandleCodeInApp(true)
                .setIOSBundleId("com.example.ios")
                .setAndroidPackageName(
                    "com.example.android",
                    true, /* installIfNotAvailable */
                    "12" /* minimumVersion */
                )
                .build()
            auth.currentUser?.sendEmailVerification(actionCodeSettings)?.await()
            emit(UiState.Success("OTP sent to $email"))
        } catch (e: Exception) {
            Log.w(TAG, "sendEmailVerification:failure", e)
            emit(UiState.Failure(e.toString()))
        }
    }


    override suspend fun verifyOtp(email: String, otp: String): Flow<UiState<Boolean>> = flow {
        try {
            emit(UiState.Loading)
            val user = auth.currentUser
            if (user != null && user.isEmailVerified) {
                emit(UiState.Success(true))
            } else {
                emit(UiState.Failure("OTP verification failed"))
            }
        } catch (e: Exception) {
            Log.w(TAG, "verifyOtp:failure", e)
            emit(UiState.Failure(e.toString()))
        }
    }



    companion object{
        private const val TAG="AuthRepositoryImpl"
    }
}