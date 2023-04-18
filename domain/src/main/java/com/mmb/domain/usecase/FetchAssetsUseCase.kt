package com.mmb.domain.usecase

import com.mmb.domain.base.IoDispatcher
import com.mmb.domain.base.Result
import com.mmb.domain.base.ResultUseCase
import com.mmb.domain.repository.AssetRepository
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

class FetchAssetsUseCase @Inject constructor(
    private val repository: AssetRepository,
    private val updateLastFetchTimeStampUseCase: UpdateLastFetchTimeStampUseCase,
    @IoDispatcher coroutineDispatcher: CoroutineDispatcher,
) : ResultUseCase<Unit, Unit>(coroutineDispatcher) {

    override suspend fun execute(parameter: Unit): Result<Unit> {
        coroutineScope {
            listOf(
                async {
                    repository.fetchAssetsIcons()
                },
                async {
                    repository.fetchAssets()
                }
            )
        }.awaitAll()

        updateLastFetchTimeStampUseCase(Unit)

        return Result.Success(Unit)
    }


}