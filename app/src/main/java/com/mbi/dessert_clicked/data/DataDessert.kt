package com.mbi.dessert_clicked.data

import androidx.annotation.DrawableRes
import com.mbi.dessert_clicked.data.Datasource.dessertList

data class DataDessert (
    val currentDessertIndex: Int = 0,
    val dessertsSold: Int = 0,
    val revenue: Int = 0,
    val currentDessertPrice: Int = dessertList[currentDessertIndex].price,
    @DrawableRes val currentDessertImageId: Int = dessertList[currentDessertIndex].imageId
)