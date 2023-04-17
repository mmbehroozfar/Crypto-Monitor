package com.mmb.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import com.mmb.data.datasource.local.model.AssetIconEntity

@Dao
interface AssetIconDao {

    @Insert
    suspend fun insert(assetIconEntities: List<AssetIconEntity>)

}