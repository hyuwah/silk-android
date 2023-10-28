package dev.hyuwah.silk.feature.home.domain.model

import androidx.annotation.DrawableRes

data class ServicePackage(
    val name: String,
    val price: Long,
    @DrawableRes val imageRes: Int,
    val place: String,
    val address: String
)
