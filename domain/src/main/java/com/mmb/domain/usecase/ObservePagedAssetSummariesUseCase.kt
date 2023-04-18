package com.mmb.domain.usecase

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mmb.domain.base.IoDispatcher
import com.mmb.domain.base.SubjectUseCase
import com.mmb.domain.model.AssetSummary
import com.mmb.domain.repository.AssetRepository
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class ObservePagedAssetSummariesUseCase @Inject constructor(
    private val repository: AssetRepository,
    @IoDispatcher coroutineDispatcher: CoroutineDispatcher,
) : SubjectUseCase<ObservePagedAssetSummariesUseCase.Params, PagingData<AssetSummary>>(
    coroutineDispatcher
) {

    private val pagingConfig = PagingConfig(
        pageSize = 15,
        prefetchDistance = 3,
        enablePlaceholders = false,
        initialLoadSize = 20,
    )

    override suspend fun createObservable(params: Params): Flow<PagingData<AssetSummary>> {
        return when {
            params.searchQuery.isNotEmpty() -> repository.observePagedAssetsBySearch(
                pagingConfig,
                params.searchQuery
            )

            params.favoriteFilterEnable -> repository.observePagedFavoriteAssets(pagingConfig)
            else -> repository.observePagedAssets(pagingConfig)
        }
    }

    data class Params(
        val searchQuery: String,
        val favoriteFilterEnable: Boolean,
    )

}