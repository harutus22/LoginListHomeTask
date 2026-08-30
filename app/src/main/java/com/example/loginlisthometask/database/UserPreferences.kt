package com.example.loginlisthometask.database

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreferences(
    private val context: Context
) {

    private val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
    private val USERNAME = stringPreferencesKey("username")

    private val Context.dataStore by preferencesDataStore(
        name = "user_preferences"
    )

    val username: Flow<String?> =
        context.dataStore.data.map { preferences ->
            preferences[USERNAME]
        }

    val isLoggedIn: Flow<Boolean> =
        context.dataStore.data.map { preferences ->
            preferences[IS_LOGGED_IN] ?: false
        }

    suspend fun setLoggedIn(username: String) {
        context.dataStore.edit { preferences ->
            preferences[IS_LOGGED_IN] = true
            preferences[USERNAME] = username
        }
    }

    suspend fun logout() {
        context.dataStore.edit { preferences ->
            preferences[IS_LOGGED_IN] = false
            preferences.remove(USERNAME)
        }
    }
}