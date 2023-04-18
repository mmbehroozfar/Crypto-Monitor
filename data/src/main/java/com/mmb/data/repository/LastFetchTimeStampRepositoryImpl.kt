package com.mmb.data.repository

import com.mmb.data.datasource.local.datasource.LastFetchTimeStampLocalDataSource
import com.mmb.domain.repository.LastFetchTimeStampRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class LastFetchTimeStampRepositoryImpl @Inject constructor(
    private val lastFetchTimeStampLocalDataSource: LastFetchTimeStampLocalDataSource,
) : LastFetchTimeStampRepository {

    override suspend fun setTimeStamp(timeStamp: Long) {
        lastFetchTimeStampLocalDataSource.setTimeStamp(timeStamp)
    }

    override fun getTimeStamp(): Flow<Long> = lastFetchTimeStampLocalDataSource.getTimeStamp()

}