package com.mmb.data.datasource.local.di

import com.mmb.data.datasource.local.datasource.AssetLocalDataSource
import com.mmb.data.datasource.local.datasource.AssetLocalDataSourceImpl
import com.mmb.data.datasource.local.datasource.LastFetchTimeStampLocalDataSource
import com.mmb.data.datasource.local.datasource.LastFetchTimeStampLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun bindsAssetLocalDataSource(impl: AssetLocalDataSourceImpl): AssetLocalDataSource

    @Binds
    @Singleton
    abstract fun bindsTimeStampLocalDataSource(impl: LastFetchTimeStampLocalDataSourceImpl): LastFetchTimeStampLocalDataSource

}