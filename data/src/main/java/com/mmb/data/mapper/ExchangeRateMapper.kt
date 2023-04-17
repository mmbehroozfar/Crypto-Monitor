package com.mmb.data.mapper

import com.mmb.data.datasource.local.model.ExchangeRateEntity
import com.mmb.data.datasource.remote.model.ExchangeRateResponse
import javax.inject.Inject

class ExchangeRateMapper @Inject constructor() {

    operator fun invoke(type: ExchangeRateResponse): ExchangeRateEntity {
        return type.let {
            ExchangeRateEntity(
                assetIdBase = it.assetIdBase,
                assetIdQuote = it.assetIdQuote,
                rate = it.rate,
                time = it.time,
            )
        }
    }
}