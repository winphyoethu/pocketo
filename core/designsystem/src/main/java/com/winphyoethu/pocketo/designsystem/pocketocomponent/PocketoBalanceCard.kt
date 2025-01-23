package com.winphyoethu.pocketo.designsystem.pocketocomponent

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.winphyoethu.pocketo.designsystem.basiccomponent.PocketoSubTitle
import com.winphyoethu.pocketo.designsystem.basiccomponent.PocketoXLTitle
import com.winphyoethu.pocketo.designsystem.ui.theme.PocketoTheme
import com.winphyoethu.pocketo.designsystem.ui.theme.mediumDp
import com.winphyoethu.pocketo.designsystem.ui.theme.xLargeDp
import com.winphyoethu.pocketo.designsystem.ui.theme.xxLargeDp
import com.winphyoethu.pocketo.designsystem.ui.theme.xxxLargeDp

/**
 * Balance Card to to be used in DashboardScreen and CreateExpenseScreen
 */
@Composable
fun PocketoBalanceCard(balance: String, currency: String, symbol: String) {
    Card(
        colors = CardDefaults.cardColors().copy(
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.onSecondary
        ),
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(xxLargeDp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(xxLargeDp)
        ) {
            PocketoSubTitle(
                subtitle = symbol,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(top = mediumDp)
            )
            PocketoXLTitle(
                title = balance,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = xLargeDp, end = xxxLargeDp)
                    .fillMaxWidth()
            )
            PocketoSubTitle(
                subtitle = currency,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(top = mediumDp)
            )
        }
    }
}

@Preview
@Composable
fun PocketoBalanceCardPreview() {
    PocketoTheme(dynamicColor = false) {
        PocketoBalanceCard("10000", "AUD", "$")
    }
}