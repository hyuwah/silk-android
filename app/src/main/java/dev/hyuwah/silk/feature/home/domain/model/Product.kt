package dev.hyuwah.silk.feature.home.domain.model

import androidx.annotation.DrawableRes

data class Product(
    val name: String,
    val price: Long,
    @DrawableRes val imageRes: Int,
    val status: Status,
    val rating: Float,
    val type: Type
) {
    enum class Status {
        Available, Unavailable
    }

    enum class Type {
        HealthService, MedicalDevice
    }
}
