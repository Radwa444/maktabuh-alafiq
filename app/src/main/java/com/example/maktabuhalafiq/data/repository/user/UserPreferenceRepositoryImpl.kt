package com.example.maktabuhalafiq.data.repository.user

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.example.maktabuhalafiq.data.datasource.datastore.DataStoreKeys.IS_USER_LOGGED_IN
import com.example.maktabuhalafiq.data.datasource.datastore.DataStoreKeys.USER_ID
import com.example.maktabuhalafiq.data.datasource.datastore.dataStore
import kotlinx.coroutines.flow.Flow

import kotlinx.coroutines.flow.map
import javax.inject.Inject


class UserPreferenceRepositoryImpl (var context: Context):UserPreferenceRepository{
    override suspend fun saveLoginState(isLoggedIn:Boolean) {
     context.dataStore.edit { preferences ->
         preferences[IS_USER_LOGGED_IN] ?: isLoggedIn
     }
 }
    // Read from DataStore
    override suspend fun isUserLoggedIn(): Flow<Boolean> {
        return context.dataStore.data
            .map { preferences ->

                preferences[IS_USER_LOGGED_IN] ?: false
            }
    }

    override suspend fun saveUserID(userID: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_ID] ?: userID
        }
    }

}