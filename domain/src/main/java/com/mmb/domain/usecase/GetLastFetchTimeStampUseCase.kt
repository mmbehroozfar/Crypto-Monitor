package com.mmb.domain.usecase

import com.mmb.domain.base.FlowUseCase
import com.mmb.domain.repository.LastFetchTimeStampRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetLastFetchTimeStampUseCase @Inject constructor(
    private val repository: LastFetchTimeStampRepository,
) : FlowUseCase<Unit, Long>() {

    override fun execute(parameter: Unit): Flow<Long> {
        return repository.getTimeStamp()
    }

}