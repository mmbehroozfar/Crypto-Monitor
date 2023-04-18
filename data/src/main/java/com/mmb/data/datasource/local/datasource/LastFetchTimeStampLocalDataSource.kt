package com.mmb.data.datasource.local.datasource

import kotlinx.coroutines.flow.Flow

interface LastFetchTimeStampLocalDataSource {

    suspend fun setTimeStamp(timeStamp: Long)

    fun getTimeStamp(): Flow<Long>

}