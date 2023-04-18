package com.mmb.data.datasource.local.model

data class AssetSummaryEntity(
    val symbol: String,
    val name: String,
    val icon: String?,
    val isFavorite: Boolean,
)
