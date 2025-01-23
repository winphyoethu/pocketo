package com.winphyoethu.pocketo.designsystem.pocketocomponent

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.winphyoethu.pocketo.designsystem.basiccomponent.PocketoSubTitle
import com.winphyoethu.pocketo.designsystem.icon.PocketoIcons
import com.winphyoethu.pocketo.designsystem.ui.theme.PocketoTheme
import com.winphyoethu.pocketo.designsystem.ui.theme.largeDp
import com.winphyoethu.pocketo.designsystem.ui.theme.mediumDp
import com.winphyoethu.pocketo.designsystem.ui.theme.xxLargeDp
import com.winphyoethu.pocketo.designsystem.ui.theme.xxxLargeDp

/**
 * Custom Appbar to be used in Pocketo app screens
 */
@Composable
fun PocketoAppbar(
    icon: ImageVector? = null,
    iconDescription: String? = null,
    iconAction: (() -> Unit)? = null,
    title: String,
    rightText: String? = null,
    rightAction: (() -> Unit)? = null
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(xxxLargeDp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            icon?.let {
                Icon(
                    imageVector = icon,
                    contentDescription = iconDescription,
                    modifier = Modifier
                        .size(xxLargeDp)
                        .clickable { iconAction?.invoke() }
                )
            }
            PocketoSubTitle(subtitle = title, modifier = Modifier.padding(start = mediumDp))
        }

        rightText?.let {
            Card(modifier = Modifier.align(Alignment.CenterEnd), onClick = {
                rightAction?.invoke()
            }) {
                PocketoSubTitle(
                    subtitle = if (it.isEmpty()) "" else it[0].toString(),
                    modifier = Modifier
                        .size(xxxLargeDp)
                        .background(
                            color = MaterialTheme.colorScheme.secondaryContainer,
                            shape = RoundedCornerShape(largeDp)
                        )
                        .wrapContentHeight(Alignment.CenterVertically),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview
@Composable
fun PocketoAppbarPreview() {
    PocketoTheme {
        Surface {
            PocketoAppbar(
                icon = PocketoIcons.Back,
                iconDescription = "Back Button",
                iconAction = {

                },
                title = "Dashboard",
                rightText = "N"
            )
        }
    }
}