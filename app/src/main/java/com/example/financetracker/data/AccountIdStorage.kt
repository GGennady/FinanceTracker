package com.example.financetracker.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AccountIdStorage @Inject constructor(private val context: Context) {
    private val Context.dataStore by preferencesDataStore(name = "account_id_datastore")

    companion object {
        val ACCOUNT_ID = intPreferencesKey("account_id")
        const val DEFAULT_ID = 21
    }

    suspend fun saveAccountId(id: Int) {
        context.dataStore.edit { mutablePreferences ->
            mutablePreferences[ACCOUNT_ID] = id
        }
    }

    // context.dataStore.data - это Flow<Preferences>
    // .map { ... } - преобразует Preferences в Int
    fun getAccountId(): Flow<Int> {
        return context.dataStore.data.map { preferences ->
            return@map preferences[ACCOUNT_ID] ?: DEFAULT_ID
        }
    }
}