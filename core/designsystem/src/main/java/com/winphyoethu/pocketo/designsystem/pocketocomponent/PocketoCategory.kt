package com.winphyoethu.pocketo.designsystem.pocketocomponent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.winphyoethu.pocketo.designsystem.basiccomponent.PocketoSubTitle
import com.winphyoethu.pocketo.designsystem.ui.theme.PocketoTheme
import com.winphyoethu.pocketo.designsystem.ui.theme.largeDp
import com.winphyoethu.pocketo.designsystem.ui.theme.mediumDp
import com.winphyoethu.pocketo.designsystem.ui.theme.xLargeDp
import com.winphyoethu.pocketo.designsystem.utils.getCategoryIcon
import com.winphyoethu.pocketo.model.category.Category
import com.winphyoethu.pocketo.model.category.mockCategory

/**
 * Category Card to be used in CreateExpenseScreen
 */
@Composable
fun PocketoCategory(category: Category, onChosen: (Category) -> Unit) {
    Card(
        colors = CardDefaults.cardColors().copy(MaterialTheme.colorScheme.secondaryContainer),
        shape = RoundedCornerShape(xLargeDp),
        onClick = { onChosen(category) },
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .padding(largeDp)
                .fillMaxWidth()
        ) {
            Icon(
                imageVector = category.name.getCategoryIcon(),
                contentDescription = category.name,
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .background(
                        MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(largeDp)
                    )
                    .padding(mediumDp)
                    .align(Alignment.CenterStart)
            )
            PocketoSubTitle(
                subtitle = category.name,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 56.dp)
            )
        }
    }
}

@Preview
@Composable
fun PocketoCategoryPreview() {
    PocketoTheme(dynamicColor = false) {
        PocketoCategory(category = mockCategory) {

        }
    }
}