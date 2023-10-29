package dev.hyuwah.silk.feature.home.domain.repository

import dev.hyuwah.silk.common.domain.model.CallResult
import dev.hyuwah.silk.feature.home.domain.model.Product
import dev.hyuwah.silk.feature.home.domain.model.ServicePackage

interface HomeRepository {
    suspend fun getProducts(): CallResult<List<Product>>
    suspend fun getServicePackages(): CallResult<List<ServicePackage>>
}