package com.mmb.data.datasource.local.di

import com.mmb.data.datasource.local.datasource.AssetLocalDataSource
import com.mmb.data.datasource.local.datasource.AssetLocalDataSourceImpl
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

}