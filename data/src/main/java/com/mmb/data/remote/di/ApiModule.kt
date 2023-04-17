package com.mmb.data.remote.di

import com.mmb.data.remote.api.AssetApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideAssetApiService(retrofit: Retrofit): AssetApiService {
        return retrofit.create(AssetApiService::class.java)
    }

}