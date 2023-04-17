package com.mmb.data.local.database

import com.mmb.data.local.dao.AssetDao
import com.mmb.data.local.dao.AssetIconDao
import com.mmb.data.local.dao.ExchangeRateDao
import com.mmb.data.local.dao.FavoriteAssetDao

interface AssetDatabase {

    fun assetDao(): AssetDao

    fun assetIconDao(): AssetIconDao

    fun exchangeRateDao(): ExchangeRateDao

    fun favoriteAssetDao(): FavoriteAssetDao

}