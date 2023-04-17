package com.mmb.data.datasource.local.database

import com.mmb.data.datasource.local.dao.AssetDao
import com.mmb.data.datasource.local.dao.AssetIconDao
import com.mmb.data.datasource.local.dao.ExchangeRateDao
import com.mmb.data.datasource.local.dao.FavoriteAssetDao

interface AssetDatabase {

    fun assetDao(): AssetDao

    fun assetIconDao(): AssetIconDao

    fun exchangeRateDao(): ExchangeRateDao

    fun favoriteAssetDao(): FavoriteAssetDao

}