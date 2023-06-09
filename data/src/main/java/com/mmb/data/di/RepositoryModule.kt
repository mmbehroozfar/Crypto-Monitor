package com.mmb.data.di

import com.mmb.data.repository.AssetRepositoryImpl
import com.mmb.data.repository.LastFetchTimeStampRepositoryImpl
import com.mmb.domain.repository.AssetRepository
import com.mmb.domain.repository.LastFetchTimeStampRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindsAssetRepository(impl: AssetRepositoryImpl): AssetRepository

    @Binds
    @Singleton
    abstract fun bindsTimeStampRepository(impl: LastFetchTimeStampRepositoryImpl): LastFetchTimeStampRepository

}