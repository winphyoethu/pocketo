package com.winphyoethu.pocketo.model.category

data class Category (
    val id: Int,
    val name: String
)

val mockCategory = Category(
    id = 0,
    name = "Food"
)