package com.mmb.domain.usecase

import com.mmb.domain.base.IoDispatcher
import com.mmb.domain.base.UseCase
import com.mmb.domain.repository.AssetRepository
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher

class FavoriteAssetUseCase @Inject constructor(
    private val repository: AssetRepository,
    @IoDispatcher coroutineDispatcher: CoroutineDispatcher,
) : UseCase<FavoriteAssetUseCase.Params, Unit>(coroutineDispatcher) {

    override suspend fun execute(parameter: Params) {
        if (!parameter.isFavorite) repository.addAssetToFavorite(parameter.id)
        else repository.removeAssetFromFavorite(parameter.id)
    }

    data class Params(
        val id: String,
        val isFavorite: Boolean,
    )
}