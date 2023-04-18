package com.mmb.data.datasource.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mmb.data.datasource.local.model.AssetEntity
import com.mmb.data.datasource.local.model.AssetSummaryEntity
import com.mmb.data.datasource.local.model.AssetWithExchangeRate
import kotlinx.coroutines.flow.Flow

@Dao
interface AssetDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAsset(asset: AssetEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAsset(assets: List<AssetEntity>)

    @Query(SELECT_ASSET_SUMMARY_QUERY)
    fun observePagedAssets(): PagingSource<Int, AssetSummaryEntity>

    @Query(SELECT_ASSET_SUMMARY_QUERY + "WHERE isFavorite = 1")
    fun observePagedFavoriteAssets(): PagingSource<Int, AssetSummaryEntity>

    @Query(SELECT_ASSET_SUMMARY_QUERY + "WHERE name LIKE '%' || :query || '%'")
    fun observePagedAssetsBySearch(query: String): PagingSource<Int, AssetSummaryEntity>

    @Query("SELECT * FROM assets WHERE symbol = :id")
    fun getAssetWithExchangeRate(id: String): Flow<AssetWithExchangeRate>

    private companion object {
        const val SELECT_ASSET_SUMMARY_QUERY =
            "SELECT assets.symbol, assets.name, asset_icons.url as icon, CASE WHEN favorite_assets.id IS NOT NULL THEN 1 ELSE 0 END as isFavorite " +
                    "FROM assets " +
                    "LEFT JOIN asset_icons ON assets.symbol = asset_icons.id " +
                    "LEFT JOIN favorite_assets ON assets.symbol = favorite_assets.symbol "
    }
}