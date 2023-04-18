package com.mmb.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mmb.data.datasource.local.datasource.AssetLocalDataSource
import com.mmb.data.datasource.local.model.FavoriteAssetEntity
import com.mmb.data.datasource.remote.datasource.AssetRemoteDataSource
import com.mmb.data.mapper.AssetIconMapper
import com.mmb.data.mapper.AssetMapper
import com.mmb.data.mapper.AssetSummaryMapper
import com.mmb.data.mapper.AssetWithExchangeRateMapper
import com.mmb.data.mapper.ExchangeRateMapper
import com.mmb.domain.extension.mapPagingData
import com.mmb.domain.model.Asset
import com.mmb.domain.model.AssetSummary
import com.mmb.domain.repository.AssetRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AssetRepositoryImpl @Inject constructor(
    private val localDataSource: AssetLocalDataSource,
    private val remoteDataSource: AssetRemoteDataSource,
    private val assetMapper: AssetMapper,
    private val assetIconMapper: AssetIconMapper,
    private val exchangeRateMapper: ExchangeRateMapper,
    private val assetSummaryMapper: AssetSummaryMapper,
    private val assetWithExchangeRateMapper: AssetWithExchangeRateMapper,
) : AssetRepository {

    override suspend fun fetchAssets() {
        remoteDataSource.getAllAssets().let { assets ->
            localDataSource.insertAssets(
                assets.map {
                    assetMapper(it)
                }
            )
        }
    }

    override suspend fun fetchAssetsIcons() {
        remoteDataSource.getAllAssetsIcons().let { assets ->
            localDataSource.insertAssetsIcons(
                assets.map {
                    assetIconMapper(it)
                }
            )
        }
    }

    override suspend fun fetchAssetDetail(id: String) {
        remoteDataSource.getAsset(id).let {
            localDataSource.insertAsset(assetMapper(it))
        }
    }

    override suspend fun fetchAssetExchangeRate(id: String) {
        remoteDataSource.getAssetExchangeRate(id).let {
            localDataSource.insertExchangeRate(exchangeRateMapper(it))
        }
    }

    override suspend fun addAssetToFavorite(id: String) {
        localDataSource.insertFavoriteAsset(FavoriteAssetEntity(symbol = id))
    }

    override suspend fun removeAssetFromFavorite(id: String) {
        localDataSource.deleteFavoriteAsset(id)
    }

    override fun observePagedAssets(pagingConfig: PagingConfig): Flow<PagingData<AssetSummary>> =
        Pager(
            config = pagingConfig,
            pagingSourceFactory = { localDataSource.observePagedAssets() },
        ).flow.mapPagingData {
            assetSummaryMapper(it)
        }

    override fun observePagedFavoriteAssets(pagingConfig: PagingConfig): Flow<PagingData<AssetSummary>> =
        Pager(
            config = pagingConfig,
            pagingSourceFactory = { localDataSource.observePagedFavoriteAssets() },
        ).flow.mapPagingData {
            assetSummaryMapper(it)
        }

    override fun observePagedAssetsBySearch(
        pagingConfig: PagingConfig,
        query: String,
    ): Flow<PagingData<AssetSummary>> = Pager(
        config = pagingConfig,
        pagingSourceFactory = { localDataSource.observePagedAssetsBySearch(query) },
    ).flow.mapPagingData {
        assetSummaryMapper(it)
    }

    override fun getAsset(id: String): Flow<Asset> {
        return localDataSource.getAssetWithExchangeRate(id).map {
            assetWithExchangeRateMapper(it)
        }
    }
}