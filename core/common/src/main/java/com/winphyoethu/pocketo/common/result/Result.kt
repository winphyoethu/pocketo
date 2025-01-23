package com.winphyoethu.pocketo.common.result

sealed class PocketoResult<out T> {

    data class Success<out T>(val data: T) : PocketoResult<T>()

    data class Error<out T>(val e: ErrorCode): PocketoResult<T>()

}