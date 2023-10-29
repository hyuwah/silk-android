package dev.hyuwah.silk.common.data.remote

import dev.hyuwah.silk.common.data.remote.response.GetUserReponse
import dev.hyuwah.silk.common.data.remote.response.LoginResponse
import dev.hyuwah.silk.common.data.remote.response.RegisterResponse
import dev.hyuwah.silk.common.domain.model.LoginRegisterRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ReqResService {

    @POST("/api/login")
    suspend fun login(
        @Body request: LoginRegisterRequest,
    ): Response<LoginResponse>

    @POST("/api/register")
    suspend fun register(
        @Body request: LoginRegisterRequest,
        ): Response<RegisterResponse>

    @GET("/api/users/{id}")
    suspend fun getUser(
        @Path("id") userId: Long,
    ): Response<GetUserReponse>
}