package dev.hyuwah.silk.feature.home.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.hyuwah.silk.R
import dev.hyuwah.silk.feature.home.data.local.DummyLocalDataSource
import dev.hyuwah.silk.feature.home.domain.model.Product
import dev.hyuwah.silk.ui.button.SilkButton
import dev.hyuwah.silk.ui.button.SilkOutlinedButton
import dev.hyuwah.silk.ui.section.CardBanner
import dev.hyuwah.silk.ui.section.CardBannerImagePos
import dev.hyuwah.silk.ui.section.GetNotificationBanner
import dev.hyuwah.silk.ui.theme.SILKTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeContent() {
    val servicePackages by remember {
        mutableStateOf(DummyLocalDataSource.getServicePackages())
    }
    val products by remember {
        mutableStateOf(DummyLocalDataSource.getProducts())
    }


    var searchQuery by remember {
        mutableStateOf("")
    }
    val productTypesFilter = listOf(
        "All Product" to null,
        "Layanan Kesehatan" to Product.Type.HealthService,
        "Alat Kesehatan" to Product.Type.MedicalDevice,
    )
    var selectedProductType by remember {
        mutableStateOf<Product.Type?>(null)
    }

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier.background(Color.White)
            ) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = null
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = null
                    )
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = null
                    )
                }
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier.padding(it),
        ) {
            // Main Banner Section
            item {
                CardBanner(
                    title = "Solusi, <b>Kesehatan Anda</b>",
                    content = "Update informasi seputar kesehatan semua bisa disini!",
                    imagePainter = painterResource(id = R.drawable.illustration_calendar),
                    highlightCard = true,
                    cardBottomSlot = {
                        SilkButton(
                            text = "Selengkapnya",
                            onClick = { /*TODO*/ },
                            modifier = Modifier
                        )
                    }
                )
                CardBanner(
                    title = "Layanan Khusus",
                    content = "Tes Covid 19, Cegah Corona Sedini Mungkin",
                    imagePainter = painterResource(id = R.drawable.illustration_vaccine),
                    cardBottomSlot = {
                        SilkOutlinedButton(
                            text = "Daftar Tes",
                            onClick = { /*TODO*/ },
                            trailingIcon = Icons.Default.ArrowForward
                        )
                    }
                )
                CardBanner(
                    title = "Track Pemeriksaan",
                    content = "Kamu dapat mengecek progress pemeriksaanmu disini",
                    imagePainter = painterResource(id = R.drawable.illustration_tracking),
                    imagePosition = CardBannerImagePos.Left,
                    cardBottomSlot = {
                        SilkOutlinedButton(
                            text = "Track",
                            onClick = { /*TODO*/ },
                            trailingIcon = Icons.Default.ArrowForward
                        )
                    }
                )
            }
            // Products & Services section (horizontal list)
            stickyHeader {
                HomeProductSearchFilter(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    searchQuery = searchQuery,
                    productTypesFilter = productTypesFilter,
                    selectedProductType = selectedProductType,
                    onSearchQuery = {
                        searchQuery = it
                    },
                    onProductTypeChanged = {
                        selectedProductType = it
                    }
                )
            }
            item {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    modifier = Modifier
                ) {
                    items(products.filter { product ->
                      product.name.lowercase().contains(searchQuery.lowercase().trim())
                              && (selectedProductType?.let { product.type == it } ?: true)
                    }, key = { it.name } ) { data ->
                        ProductCard(data = data, onClicked = {}, modifier = Modifier.animateItemPlacement())
                    }
                }
            }

            // Health service package (vertical pagination list)
            stickyHeader {
                HomeServiceTypeTab(
                    modifier = Modifier.padding(vertical = 16.dp, horizontal = 16.dp)
                )
            }

            items(servicePackages, key = { it.name }) { data ->
                ServicePackageCard(data = data, onClicked = {}, modifier = Modifier.padding(horizontal = 16.dp))
            }

            // Get notification banner
            item {
                GetNotificationBanner(
                    onGetNotificationClicked = {},
                    modifier = Modifier.padding(top = 24.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeContentPreview() {
    SILKTheme {
        HomeContent()
    }
}