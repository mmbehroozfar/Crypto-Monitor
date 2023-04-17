package com.mmb.data.mapper

import com.mmb.data.datasource.local.model.AssetEntity
import com.mmb.data.datasource.remote.model.AssetResponse
import javax.inject.Inject

class AssetMapper @Inject constructor() {

    operator fun invoke(type: AssetResponse): AssetEntity {
        return type.let {
            AssetEntity(
                id = it.assetId,
                name = it.dataEnd,
                dataEnd = it.dataOrderbookEnd,
                dataOrderbookEnd = it.dataOrderbookStart,
                dataOrderbookStart = it.dataQuoteEnd,
                dataQuoteEnd = it.dataQuoteStart,
                dataQuoteStart = it.dataStart,
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