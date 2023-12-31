package dev.hyuwah.silk.feature.home.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.hyuwah.silk.R
import dev.hyuwah.silk.feature.home.data.local.DummyLocalDataSource
import dev.hyuwah.silk.feature.home.domain.model.Product
import dev.hyuwah.silk.feature.home.presentation.component.HomeProductSearchFilter
import dev.hyuwah.silk.feature.home.presentation.component.HomeServiceTypeTab
import dev.hyuwah.silk.feature.home.presentation.component.ProductCard
import dev.hyuwah.silk.feature.home.presentation.component.ServicePackageCard
import dev.hyuwah.silk.ui.button.SilkButton
import dev.hyuwah.silk.ui.button.SilkOutlinedButton
import dev.hyuwah.silk.ui.section.CardBanner
import dev.hyuwah.silk.ui.section.CardBannerImagePos
import dev.hyuwah.silk.ui.section.GetNotificationBanner
import dev.hyuwah.silk.ui.theme.SILKTheme

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(
    state: HomeState,
    onNavMenuClicked: () -> Unit = {}
) {
    var searchQuery by remember {
        mutableStateOf("")
    }
    var selectedProductType by remember {
        mutableStateOf<Product.Type?>(null)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    navigationIconContentColor = MaterialTheme.colorScheme.primary,
                    actionIconContentColor = MaterialTheme.colorScheme.primary
                ),
                title = {},
                navigationIcon = {
                    IconButton(onClick = { onNavMenuClicked() }) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = null
                        )
                    }
                },
                actions = {
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
            )
        },
    ) {
        LazyColumn(
            modifier = Modifier.padding(it),
        ) {
            // Main Banner Section
            item {
                CardBanner(
                    title = stringResource(R.string.home_banner_1_title),
                    content = stringResource(R.string.home_banner_1_desc),
                    imagePainter = painterResource(id = R.drawable.illustration_calendar),
                    highlightCard = true,
                    cardBottomSlot = {
                        SilkButton(
                            text = stringResource(R.string.home_banner_1_cta),
                            onClick = { /*TODO*/ },
                            modifier = Modifier
                        )
                    }
                )
                CardBanner(
                    title = stringResource(R.string.home_banner_2_title),
                    content = stringResource(R.string.home_banner_2_desc),
                    imagePainter = painterResource(id = R.drawable.illustration_vaccine),
                    cardBottomSlot = {
                        SilkOutlinedButton(
                            text = stringResource(R.string.home_banner_2_cta),
                            onClick = { /*TODO*/ },
                            trailingIcon = Icons.Default.ArrowForward
                        )
                    }
                )
                CardBanner(
                    title = stringResource(R.string.home_banner_3_title),
                    content = stringResource(R.string.home_banner_3_desc),
                    imagePainter = painterResource(id = R.drawable.illustration_tracking),
                    imagePosition = CardBannerImagePos.Left,
                    cardBottomSlot = {
                        SilkOutlinedButton(
                            text = stringResource(R.string.home_banner_3_cta),
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
                    productTypesFilter = state.productTypesFilter,
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
                    items(state.products.filter { product ->
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

            items(state.servicePackages, key = { it.name }) { data ->
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
        HomeContent(
            state = HomeState()
        )
    }
}