package com.mmb.data.datasource.local.datasource

import androidx.paging.PagingSource
import com.mmb.data.datasource.local.model.AssetEntity
import com.mmb.data.datasource.local.model.AssetIconEntity
import com.mmb.data.datasource.local.model.AssetSummaryEntity
import com.mmb.data.datasource.local.model.AssetWithExchangeRate
import com.mmb.data.datasource.local.model.ExchangeRateEntity
import com.mmb.data.datasource.local.model.FavoriteAssetEntity
import kotlinx.coroutines.flow.Flow

interface AssetLocalDataSource {

    suspend fun insertAsset(entity: AssetEntity)

    suspend fun insertAssets(entities: List<AssetEntity>)

    suspend fun insertAssetsIcons(entities: List<AssetIconEntity>)

    suspend fun insertExchangeRate(entity: ExchangeRateEntity)

    suspend fun insertFavoriteAsset(entity: FavoriteAssetEntity)

    suspend fun deleteFavoriteAsset(symbol: String)

    fun observePagedAssets(): PagingSource<Int, AssetSummaryEntity>

    fun observePagedFavoriteAssets(): PagingSource<Int, AssetSummaryEntity>

    fun observePagedAssetsBySearch(query: String): PagingSource<Int, AssetSummaryEntity>

    fun getAssetWithExchangeRate(id: String): Flow<AssetWithExchangeRate>

}