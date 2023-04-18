package com.mmb.data.mapper

import com.mmb.data.datasource.local.model.AssetWithExchangeRate
import com.mmb.domain.model.Asset
import javax.inject.Inject

class AssetWithExchangeRateMapper @Inject constructor() {

    operator fun invoke(type: AssetWithExchangeRate): Asset {
        return type.let {
            Asset(
                id = it.assetEntity.symbol,
                name = it.assetEntity.name,
                dataEnd = it.assetEntity.dataEnd,
                dataOrderbookEnd = it.assetEntity.dataOrderbookEnd,
                dataOrderbookStart = it.assetEntity.dataOrderbookStart,
                dataQuoteEnd = it.assetEntity.dataQuoteEnd,
                dataQuoteStart = it.assetEntity.dataQuoteStart,
                dataStart = it.assetEntity.dataStart,
                dataTradeEnd = it.assetEntity.dataTradeEnd,
                dataTradeStart = it.assetEntity.dataTradeStart,
                volume1dayUsd = it.assetEntity.volume1dayUsd,
                volume1hrsUsd = it.assetEntity.volume1hrsUsd,
                volume1mthUsd = it.assetEntity.volume1mthUsd,
                rate = it.exchangeRateEntity?.rate ?: 0.0,
            )
        }
    }
}