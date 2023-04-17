package com.mmb.data.mapper

import com.mmb.data.datasource.local.model.AssetIconEntity
import com.mmb.data.datasource.remote.model.AssetIconResponse
import javax.inject.Inject

class AssetIconMapper @Inject constructor() {

    operator fun invoke(type: AssetIconResponse): AssetIconEntity {
        return type.let {
            AssetIconEntity(
                assetId = it.assetId,
                url = it.url,
            )
        }
    }
}