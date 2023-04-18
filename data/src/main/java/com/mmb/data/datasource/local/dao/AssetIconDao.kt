package com.mmb.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.mmb.data.datasource.local.model.AssetIconEntity

@Dao
interface AssetIconDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(assetIconEntities: List<AssetIconEntity>)

}