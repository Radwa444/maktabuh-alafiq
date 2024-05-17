package com.example.maktabuhalafiq.data.repository.user

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.example.maktabuhalafiq.data.datasource.datastore.DataStoreKeys
import com.example.maktabuhalafiq.data.datasource.datastore.dataStore

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map



class UserPreferenceRepositoryImpl(private val context: Context) : UserPreferenceRepository {


    override suspend fun saveLoginState(isLoggedIn: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[DataStoreKeys.IS_USER_LOGGED_IN] = isLoggedIn
        }
    }

    override suspend fun isUserLoggedIn(): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            preferences[DataStoreKeys.IS_USER_LOGGED_IN] ?: false
        }
    }


    override suspend fun saveUserID(userID: String) {
        context.dataStore.edit { preferences ->
            preferences[DataStoreKeys.USER_ID] = userID
        }
    }
}
