package com.winphyoethu.pocketo.designsystem.basiccomponent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.winphyoethu.pocketo.designsystem.ui.theme.AppTypography
import com.winphyoethu.pocketo.designsystem.ui.theme.PocketoTheme
import com.winphyoethu.pocketo.designsystem.ui.theme.smallDp

/**
 * Text component for Extra large title with text size 36 and bold
 */
@Composable
fun PocketoXLTitle(
    modifier: Modifier = Modifier,
    title: String,
    color: Color = Color.Unspecified,
    textAlign: TextAlign? = null
) {
    Text(
        text = title,
        color = color,
        style = AppTypography.displaySmall.plus(TextStyle(fontWeight = FontWeight.Bold)),
        maxLines = 1,
        modifier = modifier,
        textAlign = textAlign
    )
}

/**
 * Text component for title with text size 24 and bold
 */
@Composable
fun PocketoTitle(
    modifier: Modifier = Modifier,
    title: String,
    color: Color = Color.Unspecified,
    textAlign: TextAlign? = null
) {
    Text(
        text = title,
        color = color,
        style = AppTypography.headlineSmall.plus(TextStyle(fontWeight = FontWeight.Bold)),
        modifier = modifier,
        textAlign = textAlign
    )
}

/**
 * Text component for Subtitle with text size 16 and bold
 */
@Composable
fun PocketoSubTitle(
    modifier: Modifier = Modifier,
    subtitle: String,
    color: Color = Color.Unspecified,
    textAlign: TextAlign? = null
) {
    Text(
        text = subtitle,
        color = color,
        style = AppTypography.titleMedium.plus(TextStyle(fontWeight = FontWeight.Bold)),
        modifier = modifier,
        textAlign = textAlign
    )
}

/**
 * Text component for Body with text size 14
 */
@Composable
fun PocketoBody(
    modifier: Modifier = Modifier,
    body: String,
    color: Color = Color.Unspecified,
    textAlign: TextAlign? = null

) {
    Text(
        text = body,
        color = color,
        style = AppTypography.labelLarge,
        modifier = modifier,
        textAlign = textAlign
    )
}

/**
 * Text component for Label with text size 11
 */
@Composable
fun PocketoLabel(
    modifier: Modifier = Modifier, label: String, color: Color = Color.Unspecified,
    textAlign: TextAlign? = null
) {
    Text(
        text = label,
        color = color,
        style = AppTypography.labelSmall,
        modifier = modifier,
        textAlign = textAlign
    )
}

@Preview
@Composable
fun PocketoTextPreview() {
    PocketoTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(verticalArrangement = Arrangement.spacedBy(smallDp)) {
                Text("Pocketo Text", style = AppTypography.displaySmall)
                PocketoXLTitle(title = "XL Title - 36.sp")
                PocketoTitle(title = "Title - 24.sp")
                PocketoSubTitle(subtitle = "Sub Title - 16.sp")
                PocketoBody(body = "Body - 14.sp")
                PocketoLabel(label = "Label - 11.sp")
            }
        }
    }
}