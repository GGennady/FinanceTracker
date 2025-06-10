package com.example.financetracker.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.financetracker.navigation.Screen

@Composable
fun MyAccountScreen(
    onNavigateTo: (Screen) -> Unit,
) {
    Text("MyAccount")
}