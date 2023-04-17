package com.mmb.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_assets")
data class FavoriteAssetEntity(
    @PrimaryKey
    val id: String,
)