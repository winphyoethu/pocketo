package com.winphyoethu.pocketo.database.db

import androidx.room.Dao
import androidx.room.Query
import com.winphyoethu.pocketo.database.entity.CurrencyEntity

@Dao
interface CurrencyDao {

    @Query("SELECT * from currency")
    suspend fun getAllCurrencies(): List<CurrencyEntity>

}