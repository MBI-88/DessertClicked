package com.mbi.dessert_clicked


import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mbi.dessert_clicked.components.DessertClickerApp
import com.mbi.dessert_clicked.model.Dessert
import com.mbi.dessert_clicked.model.DessertViewModel
import com.mbi.dessert_clicked.theme.DessertClickerTheme


private const val TAG = "MainActivity"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG,"onCreate called")
        enableEdgeToEdge()
        setContent {
            DessertClickerTheme {
                Surface (
                    modifier = Modifier.fillMaxSize().statusBarsPadding()
                ) {
                    DessertClickerApp(
                        DessertViewModel()
                    )
                }

            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG,"onStart called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestory called")
    }
}




@Preview(showBackground = true)
@Composable
fun DessertClickerPreview() {
    DessertClickerTheme {
       DessertClickerApp(
           DessertViewModel()
       )
    }
}