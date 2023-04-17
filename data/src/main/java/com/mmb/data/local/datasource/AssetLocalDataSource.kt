package com.mmb.data.local.datasource

import androidx.paging.PagingSource
import com.mmb.data.local.model.AssetEntity
import com.mmb.data.local.model.AssetIconEntity
import com.mmb.data.local.model.AssetSummaryEntity
import com.mmb.data.local.model.AssetWithExchangeRate
import com.mmb.data.local.model.ExchangeRateEntity
import com.mmb.data.local.model.FavoriteAssetEntity
import kotlinx.coroutines.flow.Flow

interface AssetLocalDataSource {

    suspend fun insertAsset(entity: AssetEntity)

    suspend fun insertAssets(entities: List<AssetEntity>)

    suspend fun insertAssetsIcons(entities: List<AssetIconEntity>)

    suspend fun insertExchangeRate(entity: ExchangeRateEntity)

    suspend fun insertFavoriteAsset(entity: FavoriteAssetEntity)

    suspend fun deleteFavoriteAsset(entity: FavoriteAssetEntity)

    fun observePagedAssets(): PagingSource<Int, AssetSummaryEntity>

    fun observePagedFavoriteAssets(): PagingSource<Int, AssetSummaryEntity>

    fun observePagedAssetsBySearch(query: String): PagingSource<Int, AssetSummaryEntity>

    fun getAssetWithExchangeRate(id: String): Flow<AssetWithExchangeRate>

}