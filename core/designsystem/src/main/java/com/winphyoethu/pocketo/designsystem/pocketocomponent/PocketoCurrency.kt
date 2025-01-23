package com.winphyoethu.pocketo.designsystem.pocketocomponent

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.winphyoethu.pocketo.designsystem.basiccomponent.PocketoSubTitle
import com.winphyoethu.pocketo.designsystem.ui.theme.PocketoTheme
import com.winphyoethu.pocketo.designsystem.ui.theme.largeDp
import com.winphyoethu.pocketo.designsystem.ui.theme.mediumDp
import com.winphyoethu.pocketo.designsystem.ui.theme.xLargeDp
import com.winphyoethu.pocketo.model.currency.Currency
import com.winphyoethu.pocketo.model.currency.mockCurrency

/**
 * Currency Card to be used in AccountCreateScreen
 */
@Composable
fun PocketoCurrency(currency: Currency, onChosen: (Currency) -> Unit) {
    Card(
        colors = CardDefaults.cardColors().copy(MaterialTheme.colorScheme.secondaryContainer),
        shape = RoundedCornerShape(xLargeDp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onChosen(currency) }
    ) {
        Box(
            modifier = Modifier
                .padding(largeDp)
                .fillMaxWidth()
        ) {
            PocketoSubTitle(
                subtitle = currency.code,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .width(64.dp)
                    .background(
                        MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(largeDp)
                    )
                    .padding(mediumDp),
                textAlign = TextAlign.Center
            )
            PocketoSubTitle(
                subtitle = "${currency.name} (${currency.symbol})",
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 80.dp)
            )
        }
    }
}

@Preview
@Composable
fun PocketoCurrencyPreview() {
    PocketoTheme(dynamicColor = false) {
        PocketoCurrency(currency = mockCurrency.copy(code = "MMK")) {

        }
    }
}