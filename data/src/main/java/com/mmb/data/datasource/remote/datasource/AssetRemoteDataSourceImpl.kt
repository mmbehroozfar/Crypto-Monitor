package com.mmb.data.datasource.remote.datasource

import com.mmb.data.datasource.remote.api.AssetApiService
import javax.inject.Inject

class AssetRemoteDataSourceImpl @Inject constructor(
    private val apiService: AssetApiService,
) : AssetRemoteDataSource {

    override suspend fun getAllAssets() = apiService.getAllAssets()

    override suspend fun getAsset(id: String) = apiService.getAsset(id)

    override suspend fun getAllAssetsIcons() = apiService.getAllAssetsIcons()

    override suspend fun getAssetExchangeRate(id: String) = apiService.getAssetExchangeRate(id)

}