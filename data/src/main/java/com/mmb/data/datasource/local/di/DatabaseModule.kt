package com.mmb.data.datasource.local.di

import android.content.Context
import androidx.room.Room
import com.mmb.data.datasource.local.database.AssetRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
    ): AssetRoomDatabase = Room
        .databaseBuilder(context, AssetRoomDatabase::class.java, "asset-db")
        .build()

    @Provides
    @Singleton
    fun provideAssetDao(
        db: AssetRoomDatabase,
    ) = db.assetDao()

    @Provides
    @Singleton
    fun provideAssetIconDaoDao(
        db: AssetRoomDatabase,
    ) = db.assetIconDao()

    @Provides
    @Singleton
    fun provideExchangeRateDaoDao(
        db: AssetRoomDatabase,
    ) = db.exchangeRateDao()

    @Provides
    @Singleton
    fun provideFavoriteAssetDaoDao(
        db: AssetRoomDatabase,
    ) = db.favoriteAssetDao()

}