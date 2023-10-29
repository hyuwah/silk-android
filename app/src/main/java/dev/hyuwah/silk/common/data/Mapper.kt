package dev.hyuwah.silk.common.data

import dev.hyuwah.silk.common.data.remote.response.GetUserReponse
import dev.hyuwah.silk.common.domain.model.UserData

fun UserData.toUserDto(): GetUserReponse.UserDto {
    return GetUserReponse.UserDto(
        id = id,
        firstName = firstName,
        lastName = lastName,
        email = email,
        avatar = avatar
    )
}
fun GetUserReponse.UserDto.toUserData(): UserData {
    return UserData(
        id = id ?: -1,
        firstName = firstName.orEmpty(),
        lastName = lastName.orEmpty(),
        email = email.orEmpty(),
        avatar = avatar.orEmpty()
    )
}