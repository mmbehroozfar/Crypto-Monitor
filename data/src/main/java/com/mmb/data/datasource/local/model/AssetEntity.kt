package com.mmb.data.datasource.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "assets",
    indices = [
        Index(value = ["symbol"], unique = true)
    ]
)
data class AssetEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "symbol")
    val symbol: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "data_end")
    val dataEnd: String?,
    @ColumnInfo(name = "data_orderbook_end")
    val dataOrderbookEnd: String?,
    @ColumnInfo(name = "data_orderbook_start")
    val dataOrderbookStart: String?,
    @ColumnInfo(name = "data_quote_end")
    val dataQuoteEnd: String?,
    @ColumnInfo(name = "data_quote_start")
    val dataQuoteStart: String?,
    @ColumnInfo(name = "data_start")
    val dataStart: String?,
    @ColumnInfo(name = "data_trade_end")
    val dataTradeEnd: String?,
    @ColumnInfo(name = "data_trade_start")
    val dataTradeStart: String?,
    @ColumnInfo(name = "volume_1day_usd")
    val volume1dayUsd: Double,
    @ColumnInfo(name = "volume_1hrs_usd")
    val volume1hrsUsd: Double,
    @ColumnInfo(name = "volume_1mth_usd")
    val volume1mthUsd: Double,
)