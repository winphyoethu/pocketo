package com.winphyoethu.pocketo.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.winphyoethu.pocketo.database.datasource.ExpenseLocalDatasource
import com.winphyoethu.pocketo.database.entity.ExpenseEntity
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import java.util.Calendar

class ExpensePagingSource @AssistedInject constructor(
    val expenseLocalDatasource: ExpenseLocalDatasource,
//    @Assisted val startDate: Long, @Assisted val endDate: Long, @Assisted val accountId: Int,
) : PagingSource<Int, ExpenseEntity>() {

    override fun getRefreshKey(state: PagingState<Int, ExpenseEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ExpenseEntity> {
        return try {
            val position = params.key ?: 0

            val startLimit = if (position == 0) {
                0
            } else {
                position * params.loadSize
            }

            val loadSize = if (position == 0) {
                20
            } else {
                params.loadSize
            }

            val result = expenseLocalDatasource.getExpensePaginate(
                startDate = 0,
                endDate = Calendar.getInstance().timeInMillis,
                accountId = 1,
                loadSize,
                startLimit
            )

            LoadResult.Page(
                data = result,
                prevKey = if (position == 0) null else position - 1,
                nextKey = if (result.isEmpty()) null else position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

//    @AssistedFactory
//    interface Factory {
//        fun create(startDate: Long, endDate: Long, accountId: Int): ExpensePagingSource
//    }

//    @AssistedInject.Factory
//    interface Factory {
//        fun create(userId: String): UserProcessor
//    }

}