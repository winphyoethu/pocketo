package com.winphyoethu.pocketo.database.datasource

import com.winphyoethu.pocketo.database.db.CategoryDao
import com.winphyoethu.pocketo.database.entity.CategoryEntity
import javax.inject.Inject

interface CategoryLocalDatasource {

    suspend fun createCategory(category: CategoryEntity): Long

    suspend fun getAllCategory(): List<CategoryEntity>

}

class CategoryLocalDatasourceImpl @Inject constructor(val categoryDao: CategoryDao) :
    CategoryLocalDatasource {

    override suspend fun createCategory(category: CategoryEntity): Long {
        return categoryDao.createCategory(category)
    }

    override suspend fun getAllCategory(): List<CategoryEntity> {
        return categoryDao.getCategory()
    }

}