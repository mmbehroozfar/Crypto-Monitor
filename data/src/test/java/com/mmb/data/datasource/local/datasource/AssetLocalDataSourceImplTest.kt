package com.mmb.data.datasource.local.datasource

import com.google.common.truth.Truth
import com.mmb.data.datasource.MockData
import com.mmb.data.datasource.local.dao.AssetDao
import com.mmb.data.datasource.local.dao.AssetIconDao
import com.mmb.data.datasource.local.dao.ExchangeRateDao
import com.mmb.data.datasource.local.dao.FavoriteAssetDao
import com.mmb.data.datasource.local.model.AssetEntity
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.slot
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class AssetLocalDataSourceImplTest {

    private lateinit var assetLocalDataSource: AssetLocalDataSourceImpl

    @RelaxedMockK
    lateinit var assetDao: AssetDao

    @RelaxedMockK
    lateinit var assetIconDao: AssetIconDao

    @RelaxedMockK
    lateinit var exchangeRateDao: ExchangeRateDao

    @RelaxedMockK
    lateinit var favoriteAssetDao: FavoriteAssetDao

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        assetLocalDataSource = AssetLocalDataSourceImpl(
            assetDao = assetDao,
            assetIconDao = assetIconDao,
            exchangeRateDao = exchangeRateDao,
            favoriteAssetDao = favoriteAssetDao
        )
    }

    @Test
    fun insertAssetShouldCallAssetDaoInsertMethod() = runTest {
        val item = slot<AssetEntity>()
        coEvery {
            assetDao.insertAsset(capture(item))
        } returns Unit

        assetLocalDataSource.insertAsset(MockData.fakeAsset)

        coVerify(exactly = 1) { assetDao.insertAsset(MockData.fakeAsset) }
        Truth.assertThat(MockData.fakeAsset).isEqualTo(item.captured)
    }

    @Test
    fun insertAssetsShouldCallAssetDaoInsertMethod() = runTest {
        val items = slot<List<AssetEntity>>()
        coEvery {
            assetDao.insertAsset(capture(items))
        } returns Unit

        assetLocalDataSource.insertAssets(MockData.fakeAssets)

        coVerify(exactly = 1) { assetDao.insertAsset(MockData.fakeAssets) }
        Truth.assertThat(MockData.fakeAssets).isEqualTo(items.captured)
    }

}