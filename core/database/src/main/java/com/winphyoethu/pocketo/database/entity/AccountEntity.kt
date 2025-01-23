package com.winphyoethu.pocketo.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "account")
data class AccountEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo("name")
    val name: String,
    @ColumnInfo("profession")
    val profession: String,
    @ColumnInfo("income")
    val income: Double,
    @ColumnInfo("currency_id")
    val currencyId: Int,
    @ColumnInfo("created_at")
    val createdAt: Long? = null,
    @ColumnInfo("updated_at")
    val updatedAt: Long
)