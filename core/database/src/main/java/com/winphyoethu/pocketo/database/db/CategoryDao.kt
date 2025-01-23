package com.winphyoethu.pocketo.database.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.winphyoethu.pocketo.database.entity.CategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createCategory(category: CategoryEntity): Long

    @Query("SELECT * FROM category")
    suspend fun getCategory(): List<CategoryEntity>

}