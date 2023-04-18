package com.mmb.ui.asset.list

import androidx.paging.PagingData
import app.cash.turbine.test
import com.google.common.truth.Truth
import com.mmb.domain.usecase.FavoriteAssetUseCase
import com.mmb.domain.usecase.FetchAssetsUseCase
import com.mmb.domain.usecase.GetLastFetchTimeStampUseCase
import com.mmb.domain.usecase.ObservePagedAssetSummariesUseCase
import com.mmb.ui.asset.MockData
import com.mmb.ui.asset.mapper.AssetSummaryMapper
import com.mmb.ui.asset.util.CoroutineRule
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AssetListViewModelTest {


    @Rule
    @JvmField
    val coroutineRule = CoroutineRule()

    @RelaxedMockK
    lateinit var observePagedAssetSummariesUseCase: ObservePagedAssetSummariesUseCase

    @RelaxedMockK
    lateinit var fetchAssetsUseCase: FetchAssetsUseCase

    @RelaxedMockK
    lateinit var favoriteAssetsUseCase: FavoriteAssetUseCase

    @RelaxedMockK
    lateinit var getLastFetchTimeStampUseCase: GetLastFetchTimeStampUseCase

    @RelaxedMockK
    lateinit var assetSummaryMapper: AssetSummaryMapper

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun initializingShouldCallUseCases() = runTest {
        createViewModel()
        testScheduler.advanceUntilIdle()

        verify(exactly = 1) { getLastFetchTimeStampUseCase(Unit) }
        coVerify(exactly = 1) { fetchAssetsUseCase(Unit) }
        verify(exactly = 1) { observePagedAssetSummariesUseCase.flow }
        verify(exactly = 1) { observePagedAssetSummariesUseCase(any()) }
    }

    @Test
    fun onFavoriteFilterClickedShouldCallUseCases() = runTest {
        val viewModel = createViewModel()
        testScheduler.advanceUntilIdle()
        viewModel.onFavoriteFilterClicked()

        verify(exactly = 2) { observePagedAssetSummariesUseCase(any()) }
    }

    @Test
    fun onFavoriteFilterClickedShouldUpdateState() = runTest {
        val viewModel = createViewModel()
        testScheduler.advanceUntilIdle()

        Truth.assertThat(viewModel.isFavoriteFilterEnable.value).isFalse()

        viewModel.onFavoriteFilterClicked()

        Truth.assertThat(viewModel.isFavoriteFilterEnable.value).isTrue()
    }

    @Test
    fun initializingShouldCallUpdateLastFetchData() = runTest {
        every {
            getLastFetchTimeStampUseCase(Unit)
        } returns flowOf(0)
        val viewModel = createViewModel()

        Truth.assertThat(viewModel.lastFetchedData.value).isEqualTo(0)
    }

    @Test
    fun initializingShouldUpdateAssets() = runTest {
        every {
            observePagedAssetSummariesUseCase.flow
        } returns flowOf(PagingData.from(listOf(MockData.fakeAssetSummary)))
        val viewModel = createViewModel()
        testScheduler.advanceUntilIdle()

        viewModel.assets.test {
            awaitItem()
            awaitComplete()
        }
    }

    private fun createViewModel() = AssetListViewModel(
        observePagedAssetSummariesUseCase = observePagedAssetSummariesUseCase,
        fetchAssetsUseCase = fetchAssetsUseCase,
        favoriteAssetsUseCase = favoriteAssetsUseCase,
        getLastFetchTimeStampUseCase = getLastFetchTimeStampUseCase,
        assetSummaryMapper = assetSummaryMapper,
    )

}