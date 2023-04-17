package com.mmb.data.datasource.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AssetIconResponse(
    @SerialName("asset_id")
    val assetId: String,
    @SerialName("url")
    val url: String,
)