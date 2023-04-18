package com.mmb.domain.model

data class Asset(
    val id: String,
    val name: String,
    val dataEnd: String?,
    val dataOrderbookEnd: String?,
    val dataOrderbookStart: String?,
    val dataQuoteEnd: String?,
    val dataQuoteStart: String?,
    val dataStart: String?,
    val dataTradeEnd: String?,
    val dataTradeStart: String?,
    val volume1dayUsd: Double,
    val volume1hrsUsd: Double,
    val volume1mthUsd: Double,
    val rate: Double,
)