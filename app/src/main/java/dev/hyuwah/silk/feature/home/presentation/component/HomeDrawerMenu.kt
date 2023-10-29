package dev.hyuwah.silk.feature.home.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dev.hyuwah.silk.R
import dev.hyuwah.silk.navigation.Screens
import dev.hyuwah.silk.ui.button.SilkButton
import dev.hyuwah.silk.ui.modifier.plainClickable
import dev.hyuwah.silk.ui.theme.DarkBlue
import dev.hyuwah.silk.ui.theme.Grey
import dev.hyuwah.silk.ui.theme.PaleDarkBlue
import dev.hyuwah.silk.ui.theme.Red
import dev.hyuwah.silk.ui.theme.SILKTheme
import dev.hyuwah.silk.ui.theme.SilkTextStyle

@Composable
fun HomeDrawerMenu(
    navController: NavController,
    onNavigate: () -> Unit = {}
) {
    ModalDrawerSheet(
        drawerShape = RectangleShape
    ) {
        Spacer(modifier = Modifier.weight(0.1f))
        // Profile info
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 24.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
            )
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.padding(start = 16.dp)
            ) {
                Text(text = "Angga Praja", style = SilkTextStyle.cardTitle)
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Membership BBLK",
                    style = SilkTextStyle.tertiaryInfo.copy(fontSize = 14.sp, color = PaleDarkBlue)
                )
            }
        }
        Spacer(modifier = Modifier.height(40.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .plainClickable {
                    navController.navigate(Screens.Profile.route)
                    onNavigate()
                }
        ) {
            Text(
                text = "Profile Saya",
                style = SilkTextStyle.body.copy(fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
            )
            Spacer(modifier = Modifier.width(64.dp))
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = null,
                tint = Grey
            )
        }
        Spacer(modifier = Modifier.height(18.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .plainClickable {
                    navController.navigate(Screens.Profile.route)
                    onNavigate()
                }
        ) {
            Text(
                text = "Pengaturan",
                style = SilkTextStyle.body.copy(fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
            )
            Spacer(modifier = Modifier.width(64.dp))
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = null,
                tint = Grey
            )
        }
        Spacer(modifier = Modifier.height(40.dp))
        SilkButton(
            text = "Logout",
            onClick = {
                navController.navigate(Screens.Auth.route) {
                    popUpTo(Screens.Home.route) {
                        inclusive = true
                    }
                }
            },
            modifier = Modifier.padding(horizontal = 24.dp),
            shape = RoundedCornerShape(32.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Red
            )
        )
        // Social Media
        Spacer(modifier = Modifier.weight(0.1f))
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Ikuti kami di", style = SilkTextStyle.cardTitle.copy(fontSize = 16.sp))
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                painter = painterResource(id = R.drawable.ic_visibility),
                contentDescription = null,
                tint = DarkBlue
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                painter = painterResource(id = R.drawable.ic_tune),
                contentDescription = null,
                tint = DarkBlue
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                painter = painterResource(id = R.drawable.ic_business),
                contentDescription = null,
                tint = DarkBlue
            )
        }

        Spacer(modifier = Modifier.weight(0.5f))

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .fillMaxWidth()
        ) {
            Text(text = "FAQ", style = SilkTextStyle.tertiaryInfo.copy(fontWeight = FontWeight.Bold))
            Text(text = "Terms and Conditions", style = SilkTextStyle.tertiaryInfo.copy(fontWeight = FontWeight.Bold))
        }

        Spacer(modifier = Modifier.weight(0.1f))
    }
}

@Preview
@Composable
fun HomeDrawerMenuPreview() {
    SILKTheme {
        HomeDrawerMenu(navController = rememberNavController())
    }
}