package dev.hyuwah.silk.common.domain.repository

import dev.hyuwah.silk.common.domain.model.CallResult
import dev.hyuwah.silk.common.domain.model.LoginRegisterRequest

interface AuthRepository {
    suspend fun login(request: LoginRegisterRequest): CallResult<Unit>

    suspend fun register(request: LoginRegisterRequest): CallResult<Unit>
}