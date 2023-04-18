package com.mmb.data.datasource.local.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "favorite_assets",
    indices = [
        Index(value = ["symbol"], unique = true)
    ]
)
data class FavoriteAssetEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val symbol: String,
)