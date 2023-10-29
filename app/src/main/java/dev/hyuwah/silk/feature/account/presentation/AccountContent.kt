package dev.hyuwah.silk.feature.account.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.hyuwah.silk.ui.section.GetNotificationBanner
import dev.hyuwah.silk.ui.tab.SilkTab
import dev.hyuwah.silk.ui.theme.LightGrey2
import dev.hyuwah.silk.ui.theme.SILKTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountContent(
    state: AccountState,
    onNavigationBack: () -> Unit = {}
) {
    var selectedTab by remember { mutableIntStateOf(0) }
    val tabLabels = listOf("Profile Saya", "Pengaturan")

    Scaffold(
        containerColor = LightGrey2,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    navigationIconContentColor = MaterialTheme.colorScheme.primary,
                    actionIconContentColor = MaterialTheme.colorScheme.primary
                ),
                title = {},
                navigationIcon = {
                    IconButton(onClick = { onNavigationBack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
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
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxHeight()
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            SilkTab(
                selectedTabIndex = selectedTab,
                tabLabels = tabLabels,
                onTabSelected = { selectedTab = it },
                modifier = Modifier.padding(horizontal = 48.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            ProfileContent(
                userData = state.userData,
                modifier = Modifier.padding(24.dp)
            )

            Spacer(modifier = Modifier.height(48.dp))
            GetNotificationBanner(onGetNotificationClicked = {})
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AccountContentPreview() {
    SILKTheme {
        AccountContent(
            state = AccountState()
        )
    }
}