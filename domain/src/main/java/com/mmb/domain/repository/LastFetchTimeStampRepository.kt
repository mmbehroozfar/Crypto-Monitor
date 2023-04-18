package com.mmb.domain.repository

import kotlinx.coroutines.flow.Flow

interface LastFetchTimeStampRepository {

    suspend fun setTimeStamp(timeStamp: Long)

    fun getTimeStamp(): Flow<Long>

}