package dev.hyuwah.silk.feature.home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gigamole.composeshadowsplus.common.ShadowsPlusType
import com.gigamole.composeshadowsplus.common.shadowsPlus
import com.gigamole.composeshadowsplus.softlayer.SoftLayerShadowContainer
import dev.hyuwah.silk.R
import dev.hyuwah.silk.feature.home.domain.model.Product
import dev.hyuwah.silk.ui.form.SilkTextField
import dev.hyuwah.silk.ui.theme.DarkBlue
import dev.hyuwah.silk.ui.theme.SILKTheme
import dev.hyuwah.silk.ui.theme.SilkTextStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeProductSearchFilter(
    modifier: Modifier = Modifier,
    searchQuery: String = "",
    productTypesFilter: List<Pair<String, Product.Type?>> = listOf(),
    selectedProductType: Product.Type? = null,
    onSearchQuery: (query: String) -> Unit,
    onProductTypeChanged: (type: Product.Type?) -> Unit,
) {
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color.White,
                        Color.White.copy(0.8f),
                        Color.Transparent
                    )
                )
            )
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(painter = painterResource(id = R.drawable.ic_tune), contentDescription = null)
            }
            SilkTextField(
                value = searchQuery,
                onValueChange = {
                    onSearchQuery(it)
                },
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions {
                    onSearchQuery(searchQuery)
                    focusManager.clearFocus()
                },
                placeholder = {
                    Text(text = "Search", style = SilkTextStyle.body.copy(fontSize = 16.sp))
                },
                trailingIcon = {
                    IconButton(onClick = {
                        onSearchQuery(searchQuery)
                        focusManager.clearFocus()
                    }) {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "search")
                    }
                },
                roundedRadius = 32.dp,
                modifier = Modifier.weight(1f),
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .padding(bottom = 16.dp)
        ) {
            productTypesFilter.mapIndexed { index, (label, type) ->
                Spacer(modifier = Modifier.width(16.dp))
                ElevatedFilterChip(
                    selected = selectedProductType == productTypesFilter[index].second,
                    onClick = {
                        onProductTypeChanged(type)
                    },
                    label = { Text(text = label) },
                    shape = RoundedCornerShape(24.dp),
                    colors = FilterChipDefaults.elevatedFilterChipColors(
                        selectedContainerColor = DarkBlue,
                        selectedLabelColor = Color.White,
                        containerColor = Color.White,
                        labelColor = DarkBlue
                    ),
                    elevation = FilterChipDefaults.elevatedFilterChipElevation(
                        elevation = 2.dp,
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeProductSearchFilterPreview() {
    SILKTheme {
        HomeProductSearchFilter(
            productTypesFilter = listOf(
                "All" to null,
                "Type A" to Product.Type.HealthService,
                "Type B" to Product.Type.MedicalDevice
            ),
            onSearchQuery = {},
            onProductTypeChanged = {}
        )
    }
}