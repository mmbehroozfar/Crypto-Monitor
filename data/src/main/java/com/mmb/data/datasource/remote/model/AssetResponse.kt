package com.mmb.data.datasource.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AssetResponse(
    @SerialName("asset_id")
    val assetId: String,
    @SerialName("data_end")
    val dataEnd: String,
    @SerialName("data_orderbook_end")
    val dataOrderbookEnd: String,
    @SerialName("data_orderbook_start")
    val dataOrderbookStart: String,
    @SerialName("data_quote_end")
    val dataQuoteEnd: String,
    @SerialName("data_quote_start")
    val dataQuoteStart: String,
    @SerialName("data_start")
    val dataStart: String,
    @SerialName("data_symbols_count")
    val dataSymbolsCount: Int,
    @SerialName("data_trade_end")
    val dataTradeEnd: String,
    @SerialName("data_trade_start")
    val dataTradeStart: String,
    @SerialName("id_icon")
    val idIcon: String,
    @SerialName("name")
    val name: String,
    @SerialName("type_is_crypto")
    val typeIsCrypto: Int,
    @SerialName("volume_1day_usd")
    val volume1dayUsd: Double,
    @SerialName("volume_1hrs_usd")
    val volume1hrsUsd: Double,
    @SerialName("volume_1mth_usd")
    val volume1mthUsd: Double,
)