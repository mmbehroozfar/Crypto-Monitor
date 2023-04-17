package com.mmb.data.datasource.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mmb.data.datasource.local.model.AssetEntity
import com.mmb.data.datasource.local.model.AssetSummaryEntity
import com.mmb.data.datasource.local.model.AssetWithExchangeRate
import kotlinx.coroutines.flow.Flow

@Dao
interface AssetDao {

    @Insert
    suspend fun insertAsset(asset: AssetEntity)

    @Insert
    suspend fun insertAsset(assets: List<AssetEntity>)

    @Query(SELECT_ASSET_SUMMARY_QUERY)
    fun observePagedAssets(): PagingSource<Int, AssetSummaryEntity>

    @Query(SELECT_ASSET_SUMMARY_QUERY + "WHERE isFavorite = 1")
    fun observePagedFavoriteAssets(): PagingSource<Int, AssetSummaryEntity>

    @Query(SELECT_ASSET_SUMMARY_QUERY + "WHERE name LIKE '%' || :query || '%'")
    fun observePagedAssetsBySearch(query: String): PagingSource<Int, AssetSummaryEntity>

    @Query("SELECT * FROM assets WHERE id = :id")
    fun getAssetWithExchangeRate(id: String): Flow<AssetWithExchangeRate>

    private companion object {
        const val SELECT_ASSET_SUMMARY_QUERY =
            "SELECT assets.id, assets.name, asset_icons.url as icon, IFNULL(favorite_assets.id, 0) as isFavorite " +
                    "FROM assets " +
                    "LEFT JOIN asset_icons ON assets.id = asset_id " +
                    "LEFT JOIN favorite_assets ON assets.id = favorite_assets.id "
    }
}