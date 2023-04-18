package com.mmb.data.mapper

import com.mmb.data.datasource.local.model.AssetEntity
import com.mmb.data.datasource.remote.model.AssetResponse
import javax.inject.Inject

class AssetMapper @Inject constructor() {

    operator fun invoke(type: AssetResponse): AssetEntity {
        return type.let {
            AssetEntity(
                id = it.assetId.hashCode(),
                symbol = it.assetId,
                name = it.name,
                dataEnd = it.dataEnd,
                dataOrderbookEnd = it.dataOrderbookEnd,
                dataOrderbookStart = it.dataOrderbookStart,
                dataQuoteEnd = it.dataQuoteEnd,
                dataQuoteStart = it.dataQuoteStart,
                dataStart = it.dataStart,
                dataTradeEnd = it.dataTradeEnd,
                dataTradeStart = it.dataTradeStart,
                volume1dayUsd = it.volume1dayUsd,
                volume1hrsUsd = it.volume1hrsUsd,
                volume1mthUsd = it.volume1mthUsd,
            )
        }
    }

}