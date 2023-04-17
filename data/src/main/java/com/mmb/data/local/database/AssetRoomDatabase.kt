package com.mmb.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mmb.data.local.model.AssetEntity
import com.mmb.data.local.model.AssetIconEntity
import com.mmb.data.local.model.ExchangeRateEntity
import com.mmb.data.local.model.FavoriteAssetEntity

@Database(
    entities = [
        AssetEntity::class,
        AssetIconEntity::class,
        ExchangeRateEntity::class,
        FavoriteAssetEntity::class,
    ],
    version = 1,
    exportSchema = false,
)
abstract class AssetRoomDatabase : RoomDatabase(), AssetDatabase