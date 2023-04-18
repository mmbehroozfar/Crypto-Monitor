package com.mmb.data.datasource.local.model

import androidx.room.Embedded
import androidx.room.Relation

data class AssetWithExchangeRate(
    @Embedded val assetEntity: AssetEntity,
    @Relation(
        parentColumn = "symbol",
        entityColumn = "asset_id_base",
    )
    val exchangeRateEntity: ExchangeRateEntity?,
)