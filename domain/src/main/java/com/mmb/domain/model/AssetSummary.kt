package com.mmb.domain.model

data class AssetSummary(
    val id: String,
    val name: String,
    val icon: String?,
    val isFavorite: Boolean,
)