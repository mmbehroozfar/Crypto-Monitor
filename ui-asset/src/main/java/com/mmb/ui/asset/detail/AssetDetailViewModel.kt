package com.mmb.ui.asset.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mmb.domain.base.onError
import com.mmb.domain.usecase.FetchAssetExchangeRateUseCase
import com.mmb.domain.usecase.FetchAssetUseCase
import com.mmb.domain.usecase.GetAssetUseCase
import com.mmb.ui.asset.mapper.AssetMapper
import com.mmb.ui.asset.model.Asset
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class AssetDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getAssetUseCase: GetAssetUseCase,
    private val fetchAssetUseCase: FetchAssetUseCase,
    private val fetchAssetExchangeRateUseCase: FetchAssetExchangeRateUseCase,
    private val assetMapper: AssetMapper,
) : ViewModel() {

    private val _asset = MutableStateFlow(Asset())
    val asset: StateFlow<Asset> get() = _asset

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _showError = MutableSharedFlow<Unit>(replay = 0)
    val showError: SharedFlow<Unit> get() = _showError

    private val assetId = requireNotNull(savedStateHandle.get<String>("id"))

    init {
        viewModelScope.launch {
            fetchAssetExchangeRate()
        }
        getAsset()
    }

    fun fetchAllData() {
        viewModelScope.launch {
            _isLoading.value = true
            fetchAssetData()
            fetchAssetExchangeRate()
        }
    }

    private suspend fun fetchAssetData() {
        fetchAssetUseCase(assetId).onError {
            _showError.emit(Unit)
        }
    }

    private suspend fun fetchAssetExchangeRate() {
        fetchAssetExchangeRateUseCase(assetId).let {
            it.onError {
                _showError.emit(Unit)
            }
            _isLoading.value = false
        }
    }

    private fun getAsset() {
        viewModelScope.launch {
            getAssetUseCase(assetId).collect {
                _asset.value = assetMapper(it)
            }
        }
    }

}