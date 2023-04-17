package com.mmb.domain.repository

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mmb.domain.model.Asset
import com.mmb.domain.model.AssetSummary
import kotlinx.coroutines.flow.Flow

interface AssetRepository {

    suspend fun fetchAssets()

    suspend fun fetchAssetsIcons()

    suspend fun fetchAssetDetail(id: String)

    suspend fun fetchAssetExchangeRate(id: String)

    suspend fun addAssetToFavorite(id: String)

    suspend fun removeAssetFromFavorite(id: String)

    fun observePagedAssets(pagingConfig: PagingConfig): Flow<PagingData<AssetSummary>>

    fun observePagedFavoriteAssets(pagingConfig: PagingConfig): Flow<PagingData<AssetSummary>>

    fun observePagedAssetsBySearch(
        pagingConfig: PagingConfig,
        query: String,
    ): Flow<PagingData<AssetSummary>>

    fun getAsset(id: String): Flow<Asset>

}