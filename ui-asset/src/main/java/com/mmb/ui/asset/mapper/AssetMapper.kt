package com.mmb.ui.asset.mapper

import com.mmb.ui.asset.model.Asset
import javax.inject.Inject
import com.mmb.domain.model.Asset as AssetDomain

class AssetMapper @Inject constructor() {

    operator fun invoke(type: AssetDomain): Asset {
        return type.let {
            Asset(
                id = it.id,
                name = it.name,
                dataEnd = it.dataEnd ?: "",
                dataOrderbookEnd = it.dataOrderbookEnd ?: "",
                dataOrderbookStart = it.dataOrderbookStart ?: "",
                dataQuoteEnd = it.dataQuoteEnd ?: "",
                dataQuoteStart = it.dataQuoteStart ?: "",
                dataStart = it.dataStart ?: "",
                dataTradeEnd = it.dataTradeEnd ?: "",
                dataTradeStart = it.dataTradeStart ?: "",
                volume1dayUsd = it.volume1dayUsd,
                volume1hrsUsd = it.volume1hrsUsd,
                volume1mthUsd = it.volume1mthUsd,
                rate = it.rate,
            )
        }
    }
}