package com.mmb.data.datasource

import com.mmb.data.datasource.local.model.AssetEntity

object MockData {
    val fakeAsset = AssetEntity(
        id = 0,
        symbol = "",
        name = "",
        dataEnd = "",
        dataOrderbookEnd = "",
        dataOrderbookStart = "",
        dataQuoteEnd = "",
        dataQuoteStart = "",
        dataStart = "",
        dataTradeEnd = "",
        dataTradeStart = "",
        volume1dayUsd = 0.0,
        volume1hrsUsd = 0.0,
        volume1mthUsd = 0.0,
    )

    val fakeAssets = listOf(fakeAsset)
}