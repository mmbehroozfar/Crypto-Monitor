package com.mmb.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import com.mmb.data.local.model.ExchangeRateEntity

@Dao
interface ExchangeRateDao {

    @Insert
    suspend fun insert(exchangeRateEntity: ExchangeRateEntity)

}