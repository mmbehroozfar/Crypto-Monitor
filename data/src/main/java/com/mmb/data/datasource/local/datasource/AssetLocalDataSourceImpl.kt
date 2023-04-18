package com.mmb.data.datasource.local.datasource

import androidx.paging.PagingSource
import com.mmb.data.datasource.local.dao.AssetDao
import com.mmb.data.datasource.local.dao.AssetIconDao
import com.mmb.data.datasource.local.dao.ExchangeRateDao
import com.mmb.data.datasource.local.dao.FavoriteAssetDao
import com.mmb.data.datasource.local.model.AssetEntity
import com.mmb.data.datasource.local.model.AssetIconEntity
import com.mmb.data.datasource.local.model.AssetSummaryEntity
import com.mmb.data.datasource.local.model.AssetWithExchangeRate
import com.mmb.data.datasource.local.model.ExchangeRateEntity
import com.mmb.data.datasource.local.model.FavoriteAssetEntity
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class AssetLocalDataSourceImpl @Inject constructor(
    private val assetDao: AssetDao,
    private val assetIconDao: AssetIconDao,
    private val exchangeRateDao: ExchangeRateDao,
    private val favoriteAssetDao: FavoriteAssetDao,
) : AssetLocalDataSource {

    override suspend fun insertAsset(entity: AssetEntity) = assetDao.insertAsset(entity)

    override suspend fun insertAssets(entities: List<AssetEntity>) = assetDao.insertAsset(entities)

    override suspend fun insertAssetsIcons(entities: List<AssetIconEntity>) =
        assetIconDao.insert(entities)

    override suspend fun insertExchangeRate(entity: ExchangeRateEntity) =
        exchangeRateDao.insert(entity)

    override suspend fun insertFavoriteAsset(entity: FavoriteAssetEntity) =
        favoriteAssetDao.insert(entity)

    override suspend fun deleteFavoriteAsset(symbol: String) =
        favoriteAssetDao.delete(symbol)

    override fun observePagedAssets(): PagingSource<Int, AssetSummaryEntity> =
        assetDao.observePagedAssets()

    override fun observePagedFavoriteAssets(): PagingSource<Int, AssetSummaryEntity> =
        assetDao.observePagedFavoriteAssets()

    override fun observePagedAssetsBySearch(query: String): PagingSource<Int, AssetSummaryEntity> =
        assetDao.observePagedAssetsBySearch(query)

    override fun getAssetWithExchangeRate(id: String): Flow<AssetWithExchangeRate> =
        assetDao.getAssetWithExchangeRate(id)

}