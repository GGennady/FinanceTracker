package com.example.financetracker

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.view.WindowCompat
import com.example.financetracker.navigation.MainScreen
import com.example.financetracker.ui.theme.FinanceTrackerTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // disable paddings for system bars
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            FinanceTrackerTheme {
                MainScreen()
            }
        }
        //Log.d("log", Screen.Expenses.toString())
    }
}

