package com.mmb.data.datasource.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExchangeRateResponse(
    @SerialName("asset_id_base")
    val assetIdBase: String,
    @SerialName("asset_id_quote")
    val assetIdQuote: String,
    @SerialName("rate")
    val rate: Double,
    @SerialName("time")
    val time: String,
)