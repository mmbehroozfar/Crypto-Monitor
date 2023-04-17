package com.mmb.domain.usecase

import com.mmb.domain.base.Result
import com.mmb.domain.base.ResultUseCase
import com.mmb.domain.di.IoDispatcher
import com.mmb.domain.repository.AssetRepository
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher

class FetchAssetsUseCase @Inject constructor(
    private val repository: AssetRepository,
    @IoDispatcher coroutineDispatcher: CoroutineDispatcher,
) : ResultUseCase<Unit, Unit>(coroutineDispatcher) {

    override suspend fun execute(parameter: Unit): Result<Unit> {
        repository.fetchAssets()
        repository.fetchAssetsIcons()

        return Result.Success(Unit)
    }


}