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
    @DrawableRes rightIcon: Int? = null,
    onRightIconClick: ( () -> Unit )? = null,
    @DrawableRes leftIcon: Int? = null,
    onLeftIconClick: ( () -> Unit )? = null,
    colors: TopAppBarColors,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                style = Typography.titleLarge,
            )
        },

        navigationIcon = {
            if (leftIcon != null && onLeftIconClick != null) {
                IconButton(
                    onClick = onLeftIconClick,
                ) {
                    Icon(
                        painter = painterResource(id = leftIcon),
                        contentDescription = "leftIcon",
                        tint = onSurfaceVariant,
                    )
                }
            }
        },

        actions = {
            if(rightIcon != null && onRightIconClick != null) {
                IconButton(
                    onClick = onRightIconClick,
                ) {
                    Icon(
                        painter = painterResource(id = rightIcon),
                        contentDescription = "clickableIcon",
                        tint = onSurfaceVariant,
                    )
                }
            }
        },

        colors = colors,
    )
}
