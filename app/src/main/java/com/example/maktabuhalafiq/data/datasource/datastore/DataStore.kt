package com.example.maktabuhalafiq.data.datasource.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

object DataStoreKeys {
    const val E_COMMERCE_PREFERENCES = "E_Commerce_preferences"
    val IS_USER_LOGGED_IN = booleanPreferencesKey("is_user_logged_in")
    val USER_ID = stringPreferencesKey("user_id")
    val FAVORITE_BOOKS = stringSetPreferencesKey("favorite_books")
    val ARCHIVES_BOOKS = stringSetPreferencesKey("archives_books")
}

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DataStoreKeys.E_COMMERCE_PREFERENCES)

class DataStoreManager @Inject constructor(private val context: Context) {
    val archivesBooksFlow: Flow<Set<String>> = context.dataStore.data
        .map { preferences ->
        preferences[DataStoreKeys.ARCHIVES_BOOKS] ?: emptySet()
    }

    val favoriteBooksFlow: Flow<Set<String>> = context.dataStore.data
        .map { preferences ->
            preferences[DataStoreKeys.FAVORITE_BOOKS] ?: emptySet()
        }

    suspend fun toggleFavoriteBook(bookId: String) {
        context.dataStore.edit { preferences ->
            val currentFavorites = preferences[DataStoreKeys.FAVORITE_BOOKS] ?: emptySet()
            val updatedFavorites = if (currentFavorites.contains(bookId)) {
                currentFavorites - bookId
            } else {
                currentFavorites + bookId
            }
            preferences[DataStoreKeys.FAVORITE_BOOKS] = updatedFavorites
        }
    }
    suspend fun toggleArchivesBook(bookId: String) {
        context.dataStore.edit { preferences ->
            val currentFavorites = preferences[DataStoreKeys.ARCHIVES_BOOKS] ?: emptySet()
            val updatedFavorites = if (currentFavorites.contains(bookId)) {
                currentFavorites - bookId
            } else {
                currentFavorites + bookId
            }
            preferences[DataStoreKeys.ARCHIVES_BOOKS] = updatedFavorites
        }
    }
}