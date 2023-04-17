package com.mmb.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import com.mmb.data.datasource.local.model.FavoriteAssetEntity

@Dao
interface FavoriteAssetDao {

    @Insert
    suspend fun insert(favoriteAssetEntity: FavoriteAssetEntity)

    @Delete
    suspend fun delete(favoriteAssetEntity: FavoriteAssetEntity)

}