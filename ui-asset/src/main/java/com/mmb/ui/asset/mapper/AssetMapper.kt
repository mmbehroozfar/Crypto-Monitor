package com.mmb.ui.asset.mapper

import com.mmb.ui.asset.model.Asset
import javax.inject.Inject
import com.mmb.domain.model.Asset as AssetDomain

class AssetMapper @Inject constructor(
    private val dateMapper: DateMapper,
) {

    operator fun invoke(type: AssetDomain): Asset {
        return type.let {
            Asset(
                id = it.id,
                name = it.name,
                dataEnd = it.dataEnd ?: "",
                dataOrderbookEnd = it.dataOrderbookEnd?.let { date -> dateMapper(date) } ?: "",
                dataOrderbookStart = it.dataOrderbookStart?.let { date -> dateMapper(date) } ?: "",
                dataQuoteEnd = it.dataQuoteEnd?.let { date -> dateMapper(date) } ?: "",
                dataQuoteStart = it.dataQuoteStart?.let { date -> dateMapper(date) } ?: "",
                dataStart = it.dataStart ?: "",
                dataTradeEnd = it.dataTradeEnd?.let { date -> dateMapper(date) } ?: "",
                dataTradeStart = it.dataTradeStart?.let { date -> dateMapper(date) } ?: "",
                volume1dayUsd = it.volume1dayUsd,
                volume1hrsUsd = it.volume1hrsUsd,
                volume1mthUsd = it.volume1mthUsd,
                rate = it.rate,
                time = dateMapper(it.time),
            )
        }
    }
}