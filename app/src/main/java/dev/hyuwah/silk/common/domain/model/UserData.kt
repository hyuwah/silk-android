package dev.hyuwah.silk.common.domain.model

data class UserData(
    val id: Int,
    val email: String,
    val firstName: String,
    val lastName: String,
    val avatar: String,
) {
    val fullName
        get() = when {
            firstName.isNotBlank() && lastName.isNotBlank() -> "$firstName $lastName"
            firstName.isNotBlank() -> firstName
            else -> lastName
        }
}
