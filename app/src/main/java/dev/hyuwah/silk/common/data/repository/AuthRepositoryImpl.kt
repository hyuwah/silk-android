package dev.hyuwah.silk.common.data.repository

import dev.hyuwah.silk.common.data.local.LocalPreferences
import dev.hyuwah.silk.common.data.remote.ReqResService
import dev.hyuwah.silk.common.data.remote.toCallResult
import dev.hyuwah.silk.common.domain.model.CallResult
import dev.hyuwah.silk.common.domain.model.LoginRegisterRequest
import dev.hyuwah.silk.common.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val reqResService: ReqResService,
    private val localPreferences: LocalPreferences,
): AuthRepository {
    override suspend fun login(request: LoginRegisterRequest): CallResult<Unit> {
        return reqResService.login(request).toCallResult { data ->
            data.token?.let { localPreferences.token = it }
            Unit
        }
    }

    override suspend fun register(request: LoginRegisterRequest): CallResult<Unit> {
        return reqResService.register(request).toCallResult { data ->
            data.id?.let { localPreferences.userId = it.toString() }
            data.token?.let { localPreferences.token = it }
            Unit
        }
    }
}