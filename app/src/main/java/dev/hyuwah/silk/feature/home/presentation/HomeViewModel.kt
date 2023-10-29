package dev.hyuwah.silk.feature.home.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.hyuwah.silk.common.data.local.AppPreferences
import dev.hyuwah.silk.common.domain.model.CallResult
import dev.hyuwah.silk.common.domain.model.UserData
import dev.hyuwah.silk.feature.home.domain.model.Product
import dev.hyuwah.silk.feature.home.domain.model.ServicePackage
import dev.hyuwah.silk.feature.home.domain.repository.HomeRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeState(
    val productTypesFilter: List<Pair<String, Product.Type?>> = emptyList(),
    val products: List<Product> = emptyList(),
    val servicePackages: List<ServicePackage> = emptyList(),
    val userData: UserData? = null,
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    appPreferences: AppPreferences,
    homeRepository: HomeRepository
): ViewModel() {

    var state by mutableStateOf(HomeState())

    init {
        viewModelScope.launch {
            state = state.copy(
                productTypesFilter = listOf(
                    "All Product" to null,
                    "Layanan Kesehatan" to Product.Type.HealthService,
                    "Alat Kesehatan" to Product.Type.MedicalDevice,
                ),
                userData = appPreferences.userData
            )

            when (val productsResult = homeRepository.getProducts()) {
                is CallResult.Failed -> {}
                is CallResult.Success -> {
                    state = state.copy(products = productsResult.data)
                }
            }

            when (val servicePackagesResult = homeRepository.getServicePackages()) {
                is CallResult.Failed -> {}
                is CallResult.Success -> {
                    state = state.copy(servicePackages = servicePackagesResult.data)
                }
            }
        }
    }
}