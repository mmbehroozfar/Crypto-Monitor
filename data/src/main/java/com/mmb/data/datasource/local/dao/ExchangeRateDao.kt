package com.mmb.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.mmb.data.datasource.local.model.ExchangeRateEntity

@Dao
interface ExchangeRateDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(exchangeRateEntity: ExchangeRateEntity)

}