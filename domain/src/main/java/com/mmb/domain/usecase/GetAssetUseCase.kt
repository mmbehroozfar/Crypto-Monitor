package com.mmb.domain.usecase

import com.mmb.domain.base.FlowUseCase
import com.mmb.domain.model.Asset
import com.mmb.domain.repository.AssetRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetAssetUseCase @Inject constructor(
    private val repository: AssetRepository,
) : FlowUseCase<String, Asset>() {

    override fun execute(parameter: String): Flow<Asset> {
        return repository.getAsset(parameter)
    }

}