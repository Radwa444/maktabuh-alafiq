package com.example.maktabuhalafiq.data.repository.auth

import android.util.Log
import com.example.maktabuhalafiq.data.models.User
import com.example.maktabuhalafiq.utils.UiState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.database.DatabaseReference
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val databaseReference: DatabaseReference,private val  auth:FirebaseAuth):AuthRepository {
 val TAG="AuthRepositoryImpl"
    override suspend fun signup(user: User, result: (UiState<String>) -> Unit) {
       auth.createUserWithEmailAndPassword(user.email,user.password).addOnCompleteListener {
           if (it.isSuccessful){
            UiState.Success("User register successful")
               Log.d(TAG,"User register successful")

           }else{
               try {
                   throw it.exception ?: java.lang.Exception("Invalid authentication")
               } catch (e: FirebaseAuthWeakPasswordException) {
                   result.invoke(UiState.Failure("Authentication failed, Password should be at least 6 characters"))
               } catch (e: FirebaseAuthInvalidCredentialsException) {
                   result.invoke(UiState.Failure("Authentication failed, Invalid email entered"))
               } catch (e: FirebaseAuthUserCollisionException) {
                   result.invoke(UiState.Failure("Authentication failed, Email already registered."))
               } catch (e: Exception) {
                   result.invoke(UiState.Failure(e.message))
               }
           }


       }.addOnFailureListener {
           result.invoke(
               UiState.Failure(
                   it.localizedMessage
               )
           )
       }
    }
}