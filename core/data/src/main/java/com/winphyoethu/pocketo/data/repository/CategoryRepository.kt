package com.winphyoethu.pocketo.data.repository

import com.winphyoethu.pocketo.common.result.CategoryErrorCode
import com.winphyoethu.pocketo.common.result.PocketoResult
import com.winphyoethu.pocketo.database.datasource.CategoryLocalDatasource
import com.winphyoethu.pocketo.database.entity.CategoryEntity
import com.winphyoethu.pocketo.model.category.Category
import java.util.Calendar
import javax.inject.Inject

/**
 * API for CategoryRepository
 *
 * @see CategoryRepositoryImpl for actual implementation
 */
interface CategoryRepository {

    /**
     * Create Category
     * @param name Category Name
     *
     * @return PocketoResult [PocketoResult] which type is Long(id of a created category)
     */
    suspend fun createCategory(name: String): PocketoResult<Long>

    /**
     * Create Category
     *
     * @return PocketoResult [PocketoResult] which type is a list of Category [Category]
     */
    suspend fun getAllCategory(): PocketoResult<List<Category>>

}

/**
 * Implementation of CategoryRepository [CategoryRepository]
 * @see CategoryRepository
 *
 * @param datasource Local Data Source for Category [CategoryLocalDatasource]
 */
class CategoryRepositoryImpl @Inject constructor(val datasource: CategoryLocalDatasource) :
    CategoryRepository {

    override suspend fun createCategory(name: String): PocketoResult<Long> {
        val result = datasource.createCategory(
            CategoryEntity(
                name = name,
                createdAt = Calendar.getInstance().timeInMillis
            )
        )

        return if (result > 0) {
            PocketoResult.Success(result)
        } else {
            PocketoResult.Error(CategoryErrorCode.CategoryCreateFail)
        }
    }

    override suspend fun getAllCategory(): PocketoResult<List<Category>> {
        val result = datasource.getAllCategory()

        return if (result.isNotEmpty()) {
            PocketoResult.Success(result.sortedBy { it.name }.map {
                Category(id = it.id, name = it.name)
            })
        } else {
            PocketoResult.Error(CategoryErrorCode.CategoryUpdateFail)
        }
    }

}