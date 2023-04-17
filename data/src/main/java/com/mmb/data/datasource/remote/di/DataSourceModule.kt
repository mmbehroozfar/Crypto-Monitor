package com.mmb.data.datasource.remote.di

import com.mmb.data.datasource.remote.datasource.AssetRemoteDataSource
import com.mmb.data.datasource.remote.datasource.AssetRemoteDataSourceImpl
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
    abstract fun bindsAssetRemoteDataSource(impl: AssetRemoteDataSourceImpl): AssetRemoteDataSource

}