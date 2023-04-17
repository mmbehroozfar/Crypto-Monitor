package com.mmb.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "asset_icons")
data class AssetIconEntity(
    @PrimaryKey
    @ColumnInfo(name = "asset_id")
    val assetId: String,
    @ColumnInfo(name = "url")
    val url: String,
)