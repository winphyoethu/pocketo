package com.winphyoethu.pocketo.designsystem.basiccomponent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.winphyoethu.pocketo.designsystem.icon.PocketoIcons
import com.winphyoethu.pocketo.designsystem.ui.theme.PocketoTheme
import com.winphyoethu.pocketo.designsystem.ui.theme.mediumDp
import com.winphyoethu.pocketo.designsystem.ui.theme.smallDp

enum class IconPosition {
    LEADING,
    TRAILING
}

/**
 * Button designed for Pocketo app
 */
@Composable
fun PocketoButton(
    modifier: Modifier = Modifier,
    text: String,
    iconPosition: IconPosition = IconPosition.LEADING,
    icon: ImageVector? = null,
    iconDescription: String? = null,
    isEnabled: Boolean = true,
    onClick: () -> Unit
) {
    Button(onClick = onClick, enabled = isEnabled, modifier = modifier) {
        PocketoButtonContent(text, iconPosition, icon, iconDescription)
    }
}

/**
 * Outlined Button designed for Pocketo app
 */
@Composable
fun PocketoOutlinedButton(
    modifier: Modifier = Modifier,
    text: String,
    iconPosition: IconPosition = IconPosition.LEADING,
    icon: ImageVector? = null,
    iconDescription: String? = null,
    isEnabled: Boolean = true,
    onClick: () -> Unit
) {
    OutlinedButton(onClick = onClick, enabled = isEnabled, modifier = modifier) {
        PocketoButtonContent(text, iconPosition, icon, iconDescription, isOutlined = true)
    }
}

/**
 * Button content for primary button and outlined button
 */
@Composable
private fun PocketoButtonContent(
    text: String,
    iconPosition: IconPosition = IconPosition.LEADING,
    icon: ImageVector? = null,
    iconDescription: String? = null,
    isOutlined: Boolean = false
) {
    if (icon == null) {
        PocketoBody(
            body = text,
            color = if (isOutlined) Color.Unspecified else MaterialTheme.colorScheme.onPrimary
        )
    } else {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(smallDp)
        ) {
            if (iconPosition == IconPosition.LEADING) {
                Icon(
                    imageVector = icon,
                    contentDescription = iconDescription,
                    modifier = Modifier.size(20.dp)
                )
                PocketoBody(
                    body = text,
                    color = if (isOutlined) Color.Unspecified else MaterialTheme.colorScheme.onPrimary
                )
            } else {
                PocketoBody(
                    body = text,
                    color = if (isOutlined) Color.Unspecified else MaterialTheme.colorScheme.onPrimary
                )
                Icon(imageVector = icon, contentDescription = iconDescription)
            }
        }
    }
}

@Preview
@Composable
fun PocketoButtonPreview() {
    PocketoTheme(dynamicColor = false) {
        Surface {
            Column(verticalArrangement = Arrangement.spacedBy(mediumDp)) {
                Text(text = "Enabled Buttons")

                PocketoButton(text = "Primary Button") { }
                PocketoButton(text = "Primary Button", icon = PocketoIcons.Add) { }
                PocketoOutlinedButton(text = "Outlined Button") { }
                PocketoOutlinedButton(
                    text = "Outlined Button",
                    icon = PocketoIcons.Add,
                    iconPosition = IconPosition.TRAILING
                ) { }

                Text(text = "Disabled Buttons")

                PocketoButton(text = "Primary Button", isEnabled = false) { }
                PocketoButton(
                    text = "Primary Button",
                    icon = PocketoIcons.Add,
                    isEnabled = false
                ) { }
                PocketoOutlinedButton(text = "Outlined Button", isEnabled = false) { }
                PocketoOutlinedButton(
                    text = "Outlined Button",
                    icon = PocketoIcons.Add,
                    iconPosition = IconPosition.TRAILING
                    , isEnabled = false
                ) { }
            }
        }
    }
}