package dev.hyuwah.silk.feature.home.data.local

import dev.hyuwah.silk.R
import dev.hyuwah.silk.feature.home.domain.model.Product
import dev.hyuwah.silk.feature.home.domain.model.ServicePackage
import kotlin.random.Random
import kotlin.random.nextInt

object DummyLocalDataSource {

    private val productPool = listOf(
        "Suntik steril" to Product.Type.MedicalDevice,
        "Mikroskop" to Product.Type.MedicalDevice,
        "Medical Check Up" to Product.Type.HealthService,
        "Lasik" to Product.Type.HealthService,
    )

    private val dummyProducts = (1..10).map {
        val (name, type) = productPool[Random.nextInt(0, productPool.size)]
        Product(
            name = "$name $it",
            price = (Random.nextInt(10..1000) * 1000).toLong(),
            imageRes = R.drawable.img_microscope,
            status = if (Random.nextBoolean()) Product.Status.Available else Product.Status.Unavailable,
            rating = Random.nextInt(0..5).toFloat(),
            type = type
        )
    }

    private val dummyServicePackage = (1..10).map {
        ServicePackage(
            name = "PCR Swab Test (Drive Thru) $it Hari Kerja",
            price = (Random.nextInt(500..2000) * 1000).toLong(),
            imageRes = if (Random.nextBoolean()) R.drawable.img_hospital_1 else R.drawable.img_hospital_2,
            place = "Medical Institute ${Random.nextInt(1..10)}",
            address = "Street ${Random.nextInt(1..10)}, City ${Random.nextInt(1..10)}"
        )
    }

    fun getProducts(): List<Product> = dummyProducts

    fun getServicePackages(): List<ServicePackage> = dummyServicePackage

}