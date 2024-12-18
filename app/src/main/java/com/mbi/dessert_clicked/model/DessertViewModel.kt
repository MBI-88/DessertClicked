package com.mbi.dessert_clicked.model


import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import com.mbi.dessert_clicked.R
import com.mbi.dessert_clicked.data.DataDessert
import com.mbi.dessert_clicked.data.Datasource.dessertList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

import kotlinx.coroutines.flow.update

class DessertViewModel: ViewModel() {
    private val _state = MutableStateFlow(DataDessert())
    val state: StateFlow<DataDessert> = _state.asStateFlow()

    fun shareSoldDessertInfo(intentContext: Context, dessertSold: Int, revenue: Int) {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(
                Intent.EXTRA_TEXT,
                intentContext.getString(R.string.share_text, dessertSold, revenue)
            )
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent,null)
        try {
            ContextCompat.startActivity(intentContext,shareIntent, null)
        }catch (e: ActivityNotFoundException) {
            Toast.makeText(
                intentContext,
                intentContext.getString(R.string.sharing_not_available),
                Toast.LENGTH_LONG
            ).show()
        }
    }

    fun onClicked() {
        _state.update { data ->
            val dessertSold = data.dessertsSold + 1
            val nextIndex = determinateDessertIndex(data.currentDessertIndex)
            data.copy(
                currentDessertIndex = nextIndex,
                revenue = data.revenue + data.currentDessertPrice,
                dessertsSold = dessertSold
            )
        }
    }

    private  fun determinateDessertIndex(dessertsSold: Int): Int {
        var dessertIndex = 0
        for (index in dessertList.indices) {
            if (dessertsSold >= dessertList[index].startProductionAmount) {
                dessertIndex = index
            }else {
                break
            }
        }
        return dessertIndex
    }

    fun nextDessert() {
        _state.update { d ->
            val current = d.currentDessertIndex + 1
            if (current <= dessertList.size - 1) {
                println(current)
                d.copy(
                    currentDessertImageId = dessertList[current].imageId,
                    currentDessertIndex = current
                )
            }else {return}

        }
    }

    fun previewDessert() {
        _state.update { d ->
            val current = d.currentDessertIndex - 1
            if (current >= 0) {
                println(current)
                d.copy(
                    currentDessertImageId = dessertList[current].imageId,
                    currentDessertIndex = current
                )
            }else {return}

        }
    }
}