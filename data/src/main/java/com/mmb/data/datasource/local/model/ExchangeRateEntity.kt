package com.mmb.data.datasource.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exchange_rate")
data class ExchangeRateEntity(
    @PrimaryKey
    @ColumnInfo(name = "asset_id_base")
    val assetIdBase: String,
    @ColumnInfo(name = "asset_id_quote")
    val assetIdQuote: String,
    @ColumnInfo(name = "rate")
    val rate: Double,
    @ColumnInfo(name = "time")
    val time: String,
)