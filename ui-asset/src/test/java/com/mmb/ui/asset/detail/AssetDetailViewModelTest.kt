package com.mmb.ui.asset.detail

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.google.common.truth.Truth
import com.mmb.domain.base.Result
import com.mmb.domain.usecase.FetchAssetExchangeRateUseCase
import com.mmb.domain.usecase.FetchAssetUseCase
import com.mmb.domain.usecase.GetAssetUseCase
import com.mmb.ui.asset.MockData
import com.mmb.ui.asset.mapper.AssetMapper
import com.mmb.ui.asset.util.CoroutineRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.slot
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AssetDetailViewModelTest {

    @Rule
    @JvmField
    val coroutineRule = CoroutineRule()

    @RelaxedMockK
    lateinit var getAssetUseCase: GetAssetUseCase

    @RelaxedMockK
    lateinit var fetchAssetUseCase: FetchAssetUseCase

    @RelaxedMockK
    lateinit var fetchAssetExchangeRateUseCase: FetchAssetExchangeRateUseCase

    @RelaxedMockK
    lateinit var assetMapper: AssetMapper

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun initializingShouldCallGetAssetUseCase() = runTest {
        val itemId = "BTC"
        val item = slot<String>()
        every {
            getAssetUseCase(capture(item))
        } returns flowOf(MockData.fakeDomainAsset)

        createViewModel(itemId)

        coVerify(exactly = 1) { getAssetUseCase(itemId) }
        Truth.assertThat(itemId).isEqualTo(item.captured)
    }

    @Test
    fun initializingShouldSetAssetData() = runTest {
        val id = "ETH"
        every {
            getAssetUseCase(id)
        } returns flowOf(MockData.fakeDomainAsset)
        every {
            assetMapper(MockData.fakeDomainAsset)
        } returns MockData.fakeAsset

        val viewModel = createViewModel(id)

        Truth.assertThat(MockData.fakeAsset).isEqualTo(viewModel.asset.first())
    }

    @Test
    fun afterInitializingLoadingShouldBeFalse() = runTest {
        val viewModel = createViewModel()

        Truth.assertThat(viewModel.isLoading.value).isFalse()
    }

    @Test
    fun fetchAllDataShouldCallUseCases() = runTest {
        val viewModel = createViewModel()
        viewModel.fetchAllData()

        coVerify(exactly = 1) { fetchAssetUseCase(any()) }
        coVerify(exactly = 2) { fetchAssetExchangeRateUseCase(any()) }
    }

    @Test
    fun failFetchShouldEmitError() = runTest {
        coEvery {
            fetchAssetExchangeRateUseCase(any())
        } returns Result.Success(Unit)
        coEvery {
            fetchAssetUseCase(any())
        } returns Result.Error(Exception())

        val viewModel = createViewModel()

        viewModel.showError.test {
            viewModel.fetchAllData()
            awaitItem()
        }
    }

    private fun createViewModel(id: String = "ETH") = AssetDetailViewModel(
        getAssetUseCase = getAssetUseCase,
        fetchAssetUseCase = fetchAssetUseCase,
        fetchAssetExchangeRateUseCase = fetchAssetExchangeRateUseCase,
        assetMapper = assetMapper,
        savedStateHandle = SavedStateHandle(mapOf("id" to id)),
    )

}