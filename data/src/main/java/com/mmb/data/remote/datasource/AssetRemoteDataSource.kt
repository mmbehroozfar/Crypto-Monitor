package com.mmb.data.remote.datasource

import com.mmb.data.remote.model.AssetIconResponse
import com.mmb.data.remote.model.AssetResponse
import com.mmb.data.remote.model.ExchangeRateResponse

interface AssetRemoteDataSource {

    suspend fun getAllAssets(): List<AssetResponse>

    suspend fun getAsset(id: String): AssetResponse

    suspend fun getAllAssetsIcons(): List<AssetIconResponse>

    suspend fun getAssetExchangeRate(id: String): ExchangeRateResponse

}