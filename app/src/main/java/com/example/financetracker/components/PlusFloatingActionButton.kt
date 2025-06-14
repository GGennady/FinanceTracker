package com.example.financetracker.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.financetracker.R
import com.example.financetracker.ui.theme.Green
import com.example.financetracker.ui.theme.White

@Composable
fun PlusFloatingActionButton(
    modifier: Modifier,
    onClick: () -> Unit
) {
    FloatingActionButton(
        modifier = modifier,
        onClick = onClick,
        containerColor = Green,
        contentColor = White,
        shape = CircleShape,
        elevation = FloatingActionButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
            focusedElevation = 0.dp,
            hoveredElevation = 0.dp
        ),
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_fab_plus),
            contentDescription = "plusFloatingActionButton",
        )
    }
}