package com.mmb.data.datasource.remote.api

import com.mmb.data.datasource.remote.model.AssetIconResponse
import com.mmb.data.datasource.remote.model.AssetResponse
import com.mmb.data.datasource.remote.model.ExchangeRateResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface AssetApiService {

    @GET("v1/assets")
    suspend fun getAllAssets(): List<AssetResponse>

    @GET("v1/assets/{id}")
    suspend fun getAsset(@Path("id") id: String): List<AssetResponse>

    @GET("v1/assets/icons/32")
    suspend fun getAllAssetsIcons(): List<AssetIconResponse>

    @GET("v1/exchangerate/{id}/EUR")
    suspend fun getAssetExchangeRate(@Path("id") id: String): ExchangeRateResponse

}