package com.winphyoethu.pocketo.designsystem.utils

import androidx.compose.ui.graphics.vector.ImageVector
import com.winphyoethu.pocketo.designsystem.icon.PocketoIcons

fun String.getCategoryIcon(): ImageVector {
    return when (this) {
        "Rent" -> PocketoIcons.Rent
        "Bill" -> PocketoIcons.Bill
        "Grocery" -> PocketoIcons.Grocery
        "Eat Out" -> PocketoIcons.EatOut
        "Subscription" -> PocketoIcons.Subscription
        "Gift" -> PocketoIcons.Gift
        "Coffee" -> PocketoIcons.Coffee
        "Insurance" -> PocketoIcons.Insurance
        "Clothing" -> PocketoIcons.Clothing
        "Top Up" -> PocketoIcons.TopUp
        "ATM" -> PocketoIcons.ATM
        else -> PocketoIcons.Others
    }
}