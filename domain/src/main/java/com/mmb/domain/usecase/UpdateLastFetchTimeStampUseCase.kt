package com.mmb.domain.usecase

import com.mmb.domain.base.IoDispatcher
import com.mmb.domain.base.UseCase
import com.mmb.domain.repository.LastFetchTimeStampRepository
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher

class UpdateLastFetchTimeStampUseCase @Inject constructor(
    private val repository: LastFetchTimeStampRepository,
    @IoDispatcher coroutineDispatcher: CoroutineDispatcher,
) : UseCase<Unit, Unit>(coroutineDispatcher) {

    override suspend fun execute(parameter: Unit) {
        repository.setTimeStamp(System.currentTimeMillis())
    }

}