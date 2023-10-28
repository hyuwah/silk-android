package dev.hyuwah.silk.ui.section

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.text.parseAsHtml
import com.gigamole.composeshadowsplus.common.ShadowsPlusType
import com.gigamole.composeshadowsplus.common.shadowsPlus
import com.gigamole.composeshadowsplus.softlayer.SoftLayerShadowContainer
import dev.hyuwah.silk.R
import dev.hyuwah.silk.ui.button.SilkButton
import dev.hyuwah.silk.ui.button.SilkOutlinedButton
import dev.hyuwah.silk.ui.text.toAnnotatedString
import dev.hyuwah.silk.ui.theme.Grey
import dev.hyuwah.silk.ui.theme.PaleDarkBlue
import dev.hyuwah.silk.ui.theme.SILKTheme
import dev.hyuwah.silk.ui.theme.SilkTextStyle

enum class CardBannerImagePos {
    Left, Right
}

@Composable
fun CardBanner(
    title: String,
    content: String,
    imagePainter: Painter,
    imagePosition: CardBannerImagePos = CardBannerImagePos.Right,
    highlightCard: Boolean = false,
    modifier: Modifier = Modifier,
    cardBottomSlot: @Composable () -> Unit
) {
    val highlightedBackground = Modifier.background(brush = Brush.linearGradient(listOf(Color.White, PaleDarkBlue.copy(alpha = 0.25f))))
    ConstraintLayout(
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        val (image, card, textContent, button) = createRefs()
        SoftLayerShadowContainer {
            Surface(
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(card) {
                        top.linkTo(parent.top, margin = 32.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }
                    .shadowsPlus(
                        type = ShadowsPlusType.SoftLayer,
                        radius = if (highlightCard) 6.dp else 4.dp,
                        spread = if (highlightCard) 3.dp else 2.dp,
                        color = Grey.copy(alpha = if (highlightCard) 0.2f else 0.1f),
                        offset = DpOffset(0.dp, 4.dp),
                        shape = RoundedCornerShape(16.dp),

                    )
            ) {
                Row(
                    modifier = if (highlightCard) highlightedBackground else Modifier
                ) {
                    if (imagePosition == CardBannerImagePos.Left) {
                        Spacer(modifier = Modifier.weight(0.35f))
                    }
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxWidth(0.65f)
                        ) {
                            Text(
                                text = title.parseAsHtml().toAnnotatedString(),
                                style = SilkTextStyle.cardTitle
                            )
                            Spacer(Modifier.height(8.dp))
                            Text(
                                text = content,
                                style = SilkTextStyle.cardContent
                            )
                        }
                        Spacer(Modifier.height(8.dp))
                        cardBottomSlot()
                    }
                }
            }

        }
        Image(
            painter = imagePainter,
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth(0.35f)
                .height(120.dp)
                .padding(horizontal = 12.dp)
                .constrainAs(image) {
                    top.linkTo(parent.top)
                    when (imagePosition) {
                        CardBannerImagePos.Left -> {
                            start.linkTo(parent.start)
                        }

                        CardBannerImagePos.Right -> {
                            end.linkTo(parent.end)
                        }
                    }
                }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CardBannerRightImagePreview() {
    SILKTheme {
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
    }
}

@Preview(showBackground = true)
@Composable
fun CardBannerLeftImagePreview() {
    SILKTheme {
        CardBanner(
            title = "Layanan Khusus",
            content = "Tes Covid 19, Cegah Corona Sedini Mungkin",
            imagePainter = painterResource(id = R.drawable.illustration_tracking),
            imagePosition = CardBannerImagePos.Left,
            cardBottomSlot = {
                SilkOutlinedButton(
                    text = "Selengkapnya",
                    onClick = { /*TODO*/ },
                    trailingIcon = Icons.Default.ArrowForward
                )
            }
        )
    }
}