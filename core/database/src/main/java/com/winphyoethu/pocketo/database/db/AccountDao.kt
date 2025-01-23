package com.winphyoethu.pocketo.database.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.winphyoethu.pocketo.database.entity.AccountEntity
import com.winphyoethu.pocketo.database.entity.AccountFullInfo

@Dao
interface AccountDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createAccount(account: AccountEntity): Long

    @Update
    suspend fun updateAccount(account: AccountEntity): Int

    @Delete
    suspend fun deleteAccount(account: AccountEntity): Int

    @Query("SELECT * FROM account")
    suspend fun getAllAccount(): List<AccountFullInfo>

    @Query("SELECT * FROM account WHERE id = :id")
    suspend fun getAccountById(id: Int): AccountFullInfo?

}