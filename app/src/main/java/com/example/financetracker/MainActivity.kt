package com.example.financetracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.core.view.WindowCompat
import com.example.financetracker.di.components.DaggerMainActivityComponent
import com.example.financetracker.di.components.MainActivityComponent
import com.example.financetracker.presentation.LocalViewModelFactory
import com.example.financetracker.presentation.navigation.MainScreen
import com.example.financetracker.presentation.screens.SplashScreen
import com.example.financetracker.ui.theme.FinanceTrackerTheme

class MainActivity : ComponentActivity() {

    lateinit var mainActivityComponent: MainActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val app = application as FinanceTrackerApp

        mainActivityComponent = DaggerMainActivityComponent.factory().create(this, app.appComponent)

        enableEdgeToEdge()

        // disable paddings for system bars
        WindowCompat.setDecorFitsSystemWindows(window, false)


        setContent {
            FinanceTrackerTheme {
                CompositionLocalProvider(LocalViewModelFactory provides mainActivityComponent.viewModelProviderFactory()) {
                    AppEntryPoint()
                }
            }
        }
    }
}

@Composable
fun AppEntryPoint() {
    var showSplash by remember { mutableStateOf(true) }

    if (showSplash) {
        SplashScreen {
            showSplash = false
        }
    } else {
        MainScreen()
    }
}

