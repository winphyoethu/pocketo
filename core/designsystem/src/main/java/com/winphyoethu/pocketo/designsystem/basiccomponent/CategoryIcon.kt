package com.winphyoethu.pocketo.designsystem.basiccomponent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.winphyoethu.pocketo.designsystem.icon.PocketoIcons
import com.winphyoethu.pocketo.designsystem.ui.theme.PocketoTheme
import com.winphyoethu.pocketo.designsystem.ui.theme.primaryLight

/**
 * Category Icon to be used in ExpenseCard
 */
@Composable
fun CategoryIcon(icon: ImageVector, description: String) {

    Box(
        modifier = Modifier
            .padding(8.dp)
            .background(primaryLight, shape = RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Icon(imageVector = icon, contentDescription = description, tint = Color.White)
    }

}

@Preview
@Composable
fun CategoryIconPreview() {
    PocketoTheme {
        CategoryIcon(PocketoIcons.Bill, "Bill")
    }
}