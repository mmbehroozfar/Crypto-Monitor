package com.mmb.data.datasource.remote.datasource

import com.mmb.data.datasource.remote.model.AssetIconResponse
import com.mmb.data.datasource.remote.model.AssetResponse
import com.mmb.data.datasource.remote.model.ExchangeRateResponse

interface AssetRemoteDataSource {

    suspend fun getAllAssets(): List<AssetResponse>

    suspend fun getAsset(id: String): AssetResponse

    suspend fun getAllAssetsIcons(): List<AssetIconResponse>

    suspend fun getAssetExchangeRate(id: String): ExchangeRateResponse

}