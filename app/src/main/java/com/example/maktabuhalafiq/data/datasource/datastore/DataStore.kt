package com.example.maktabuhalafiq.data.datasource.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.maktabuhalafiq.data.datasource.datastore.DataStoreKeys.E_COMMERCE_PREFERENCES


object DataStoreKeys{
    const val E_COMMERCE_PREFERENCES="E_Commerce_preferences"
    val IS_USER_LOGGED_IN= booleanPreferencesKey("is_user_logged_in")
    val USER_ID= booleanPreferencesKey("user_id")
}
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = E_COMMERCE_PREFERENCES)
