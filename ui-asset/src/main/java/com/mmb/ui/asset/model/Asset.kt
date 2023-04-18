package com.mmb.ui.asset.model

data class Asset(
    val id: String = "",
    val name: String = "",
    val dataEnd: String = "",
    val dataOrderbookEnd: String = "",
    val dataOrderbookStart: String = "",
    val dataQuoteEnd: String = "",
    val dataQuoteStart: String = "",
    val dataStart: String = "",
    val dataTradeEnd: String = "",
    val dataTradeStart: String = "",
    val volume1dayUsd: Double = 0.0,
    val volume1hrsUsd: Double = 0.0,
    val volume1mthUsd: Double = 0.0,
    val rate: Double = 0.0,
    val time: String = "",
)