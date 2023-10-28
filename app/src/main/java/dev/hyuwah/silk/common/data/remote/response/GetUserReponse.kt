package dev.hyuwah.silk.common.data.remote.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetUserReponse(
    @Json(name = "data")
    val `data`: Data?
) {
    @JsonClass(generateAdapter = true)
    data class Data(
        @Json(name = "avatar")
        val avatar: String?,
        @Json(name = "email")
        val email: String?,
        @Json(name = "first_name")
        val firstName: String?,
        @Json(name = "id")
        val id: Int?,
        @Json(name = "last_name")
        val lastName: String?
    )
}