package com.mmb.ui.asset.mapper

import com.mmb.ui.asset.model.AssetSummary
import javax.inject.Inject
import com.mmb.domain.model.AssetSummary as AssetSummaryDomain

class AssetSummaryMapper @Inject constructor() {

    operator fun invoke(type: AssetSummaryDomain): AssetSummary {
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