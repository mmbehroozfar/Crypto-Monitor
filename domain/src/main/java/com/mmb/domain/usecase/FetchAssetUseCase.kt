package com.mmb.domain.usecase

import com.mmb.domain.base.Result
import com.mmb.domain.base.ResultUseCase
import com.mmb.domain.di.IoDispatcher
import com.mmb.domain.repository.AssetRepository
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher

class FetchAssetUseCase @Inject constructor(
    private val repository: AssetRepository,
    @IoDispatcher coroutineDispatcher: CoroutineDispatcher,
) : ResultUseCase<String, Unit>(coroutineDispatcher) {

    override suspend fun execute(parameter: String): Result<Unit> {
        repository.fetchAssetDetail(parameter)
        repository.fetchAssetExchangeRate(parameter)

        return Result.Success(Unit)
    }


}