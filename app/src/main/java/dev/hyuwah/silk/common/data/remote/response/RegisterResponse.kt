package dev.hyuwah.silk.common.data.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RegisterResponse(
    @Json(name = "id")
    val id: Long?,
    @Json(name = "token")
    val token: String?
)
