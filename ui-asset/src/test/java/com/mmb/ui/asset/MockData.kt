package com.mmb.ui.asset

import com.mmb.ui.asset.model.Asset
import com.mmb.domain.model.Asset as DomainAsset

object MockData {

    val fakeDomainAsset = DomainAsset(
        id = "BTC",
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
        rate = 0.0,
        time = "",
    )

    val fakeAsset = Asset(
        id = "BTC",
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
        rate = 0.0,
        time = "",
    )

}