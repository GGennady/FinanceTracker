package com.example.financetracker

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * The application class for the Finance Tracker app.
 *
 * Initializes Hilt for DI.
 */
@HiltAndroidApp
class FinanceTrackerApp: Application()