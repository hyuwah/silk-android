package dev.hyuwah.silk.common.domain.model

import java.lang.Exception

sealed interface CallResult<out T> {
    data class Success<out T>(
        val data: T
    ): CallResult<T>
    data class Failed(
        val httpCode: Int? = null,
        val exception: Exception? = null
    ): CallResult<Nothing>
}