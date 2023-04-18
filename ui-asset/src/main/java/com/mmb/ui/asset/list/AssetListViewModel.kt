package com.mmb.ui.asset.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.mmb.domain.base.onError
import com.mmb.domain.extension.mapPagingData
import com.mmb.domain.usecase.FavoriteAssetUseCase
import com.mmb.domain.usecase.FetchAssetsUseCase
import com.mmb.domain.usecase.GetLastFetchTimeStampUseCase
import com.mmb.domain.usecase.ObservePagedAssetSummariesUseCase
import com.mmb.ui.asset.mapper.AssetSummaryMapper
import com.mmb.ui.asset.model.AssetSummary
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@HiltViewModel
class AssetListViewModel @Inject constructor(
    private val observePagedAssetSummariesUseCase: ObservePagedAssetSummariesUseCase,
    private val fetchAssetsUseCase: FetchAssetsUseCase,
    private val favoriteAssetsUseCase: FavoriteAssetUseCase,
    private val getLastFetchTimeStampUseCase: GetLastFetchTimeStampUseCase,
    private val assetSummaryMapper: AssetSummaryMapper,
) : ViewModel() {

    private val _assets =
        observePagedAssetSummariesUseCase.flow.mapPagingData { assetSummaryMapper(it) }
    val assets: Flow<PagingData<AssetSummary>> get() = _assets

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _isSearchModeEnable = MutableStateFlow(false)
    val isSearchModeEnable: StateFlow<Boolean> get() = _isSearchModeEnable

    private val _isFavoriteFilterEnable = MutableStateFlow(false)
    val isFavoriteFilterEnable: StateFlow<Boolean> get() = _isFavoriteFilterEnable

    private val _searchQuery = MutableStateFlow("")

    private val _lastFetchedData = MutableStateFlow(0L)
    val lastFetchedData: StateFlow<Long> get() = _lastFetchedData

    private val _showError = MutableSharedFlow<Unit>(replay = 0)
    val showError: SharedFlow<Unit> get() = _showError

    init {
        getLastFetchedDataTimeStamp()
        observeSearchQuery()
        fetchAssets()
    }

    fun onFavoriteFilterClicked() {
        viewModelScope.launch {
            val currentValue = _isFavoriteFilterEnable.value
            updatePagedAssets(isFavorite = !currentValue)
            _isFavoriteFilterEnable.value = !currentValue
        }
    }

    fun onFavoriteAssetClicked(asset: AssetSummary) {
        viewModelScope.launch {
            favoriteAssetsUseCase(FavoriteAssetUseCase.Params(asset.id, asset.isFavorite))
        }
    }

    fun onSearchQueryChanged(query: String?) {
        _searchQuery.value = query ?: ""
    }

    fun onSearchModeChanged(isEnable: Boolean) {
        _isSearchModeEnable.value = isEnable
    }

    private fun getLastFetchedDataTimeStamp() {
        viewModelScope.launch {
            getLastFetchTimeStampUseCase(Unit).collect {
                _lastFetchedData.value = it
            }
        }
    }

    private fun fetchAssets() {
        viewModelScope.launch {
            fetchAssetsUseCase(Unit).let {
                it.onError {
                    _showError.emit(Unit)
                }
                _isLoading.value = false
            }
        }
    }

    private fun updatePagedAssets(
        searchQuery: String = _searchQuery.value,
        isFavorite: Boolean = _isFavoriteFilterEnable.value,
    ) {
        observePagedAssetSummariesUseCase(
            ObservePagedAssetSummariesUseCase.Params(
                searchQuery = searchQuery,
                favoriteFilterEnable = isFavorite,
            )
        )
    }

    private fun observeSearchQuery() {
        _searchQuery
            .debounce(500)
            .onEach {
                updatePagedAssets(it)
            }
            .launchIn(viewModelScope)
    }
}