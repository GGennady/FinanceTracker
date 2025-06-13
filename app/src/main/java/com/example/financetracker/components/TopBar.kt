package com.example.financetracker.components

import androidx.annotation.DrawableRes
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.example.financetracker.ui.theme.Typography
import com.example.financetracker.ui.theme.onSurfaceVariant

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TopBar(
    title: String,
    @DrawableRes clickableIcon: Int? = null,
    onIconClick: ( () -> Unit )? = null,
    colors: TopAppBarColors,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                style = Typography.titleLarge,
            )
        },

        actions = {
            if(clickableIcon != null && onIconClick != null) {
                IconButton(
                    onClick = onIconClick,
                ) {
                    Icon(
                        painter = painterResource(id = clickableIcon),
                        contentDescription = "clickableIcon",
                        tint = onSurfaceVariant,
                    )
                }
            }
        },

        colors = colors,
    )
}
