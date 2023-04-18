package com.mmb.data.datasource.local.datasource

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LastFetchTimeStampLocalDataSourceImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : LastFetchTimeStampLocalDataSource {

    private val Context.userPreferencesDataStore: DataStore<Preferences> by preferencesDataStore(
        name = "timeStampStore",
    )
    private val timeStampKey = longPreferencesKey("time_stamp")

    override suspend fun setTimeStamp(timeStamp: Long) {
        context.userPreferencesDataStore.edit { preferences ->
            preferences[timeStampKey] = timeStamp
        }
    }

    override fun getTimeStamp(): Flow<Long> {
        return context.userPreferencesDataStore.data.map { preferences ->
            preferences[timeStampKey] ?: 0
        }
    }

}