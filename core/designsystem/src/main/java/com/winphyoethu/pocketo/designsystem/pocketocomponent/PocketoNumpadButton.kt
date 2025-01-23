package com.winphyoethu.pocketo.designsystem.pocketocomponent

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.winphyoethu.pocketo.designsystem.basiccomponent.PocketoXLTitle
import com.winphyoethu.pocketo.designsystem.ui.theme.PocketoTheme

/**
 * Numpad Button to be used in CreateExpenseScreen
 */
@Composable
fun NumpadButton(num: String, onNumPadClicked: (num: String) -> Unit) {
    Button(
        colors = ButtonDefaults.buttonColors()
            .copy(
                containerColor = Color.White,
                contentColor = Color.Black
            ), shape = CircleShape,
        modifier = Modifier
            .clip(CircleShape)
            .size(100.dp),
        onClick = { onNumPadClicked(num) }) {
        PocketoXLTitle(title = num)
    }
}

@Composable
@Preview
fun NumpadButtonPreview() {
    PocketoTheme {
        NumpadButton("1") {

        }
    }
}