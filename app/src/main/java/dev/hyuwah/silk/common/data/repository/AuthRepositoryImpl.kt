package dev.hyuwah.silk.common.data.repository

import dev.hyuwah.silk.common.data.local.AppPreferences
import dev.hyuwah.silk.common.data.remote.ReqResService
import dev.hyuwah.silk.common.data.remote.toCallResult
import dev.hyuwah.silk.common.data.toUserData
import dev.hyuwah.silk.common.domain.model.CallResult
import dev.hyuwah.silk.common.domain.model.LoginRegisterRequest
import dev.hyuwah.silk.common.domain.repository.AuthRepository
import javax.inject.Inject
import kotlin.random.Random
import kotlin.random.nextLong

class AuthRepositoryImpl @Inject constructor(
    private val reqResService: ReqResService,
    private val appPreferences: AppPreferences,
) : AuthRepository {
    override suspend fun login(request: LoginRegisterRequest): CallResult<Unit> {
        val loginResult = reqResService.login(request).toCallResult { data ->
            data.token?.let { appPreferences.token = it }
            Unit
        }
        // Fetch User profile
        reqResService.getUser(Random.nextLong(1L..10L)).toCallResult {
            val userData = it.userDto?.toUserData()
            appPreferences.userData = userData
            userData
        }
        return loginResult
    }

    override suspend fun register(request: LoginRegisterRequest): CallResult<Unit> {
        var registerId: Long? = null
        val registerResult = reqResService.register(request).toCallResult { data ->
            data.id?.let { registerId = it }
            data.token?.let { appPreferences.token = it }
            Unit
        }
        // Fetch User profile
        reqResService.getUser(registerId ?: Random.nextLong(1L..10L)).toCallResult {
            val userData = it.userDto?.toUserData()
            appPreferences.userData = userData
            userData
        }
        return registerResult
    }
}