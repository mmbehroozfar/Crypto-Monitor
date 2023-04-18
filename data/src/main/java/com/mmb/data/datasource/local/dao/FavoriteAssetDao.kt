package com.mmb.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mmb.data.datasource.local.model.FavoriteAssetEntity

@Dao
interface FavoriteAssetDao {

    @Insert
    suspend fun insert(favoriteAssetEntity: FavoriteAssetEntity)

    @Query("DELETE FROM favorite_assets WHERE symbol = :symbol")
    suspend fun delete(symbol: String)

}