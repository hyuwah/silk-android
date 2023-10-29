package dev.hyuwah.silk.feature.home.data

import dev.hyuwah.silk.common.domain.model.CallResult
import dev.hyuwah.silk.feature.home.data.local.DummyLocalDataSource
import dev.hyuwah.silk.feature.home.domain.repository.HomeRepository
import dev.hyuwah.silk.feature.home.domain.model.Product
import dev.hyuwah.silk.feature.home.domain.model.ServicePackage
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(): HomeRepository {
    override suspend fun getProducts(): CallResult<List<Product>> {
        return CallResult.Success(DummyLocalDataSource.getProducts())
    }

    override suspend fun getServicePackages(): CallResult<List<ServicePackage>> {
        return CallResult.Success(DummyLocalDataSource.getServicePackages())
    }

}