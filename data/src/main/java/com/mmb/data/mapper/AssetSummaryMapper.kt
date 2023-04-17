package com.mmb.data.mapper

import com.mmb.data.datasource.local.model.AssetSummaryEntity
import com.mmb.domain.model.AssetSummary
import javax.inject.Inject

class AssetSummaryMapper @Inject constructor() {

    operator fun invoke(type: AssetSummaryEntity): AssetSummary {
        return type.let {
            AssetSummary(
                id = it.id,
                name = it.name,
                icon = it.icon,
                isFavorite = it.isFavorite,
            )
        }
    }
}