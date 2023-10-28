package dev.hyuwah.silk.feature.authentication.domain.model

data class RegistrationData(
    val firstName: String = "",
    val lastName: String = "",
    val nationalIdNumber: String = "",
    val email: String = "",
    val phoneNumber: String = "",
    val password: String = ""
)
