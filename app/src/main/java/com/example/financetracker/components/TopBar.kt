package com.example.financetracker.components


import androidx.annotation.DrawableRes
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.financetracker.ui.theme.Green
import com.example.financetracker.ui.theme.Typography
import com.example.financetracker.ui.theme.onSurface
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

        // "Do not apply any automatic indents to system bars (e.g. status bar, navbar, etc.)"
        // windowInsets = WindowInsets(top = 0, bottom = 0, left = 0, right = 0),
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
private fun TopBarPreview() {
    TopBar("Привет", colors = TopAppBarDefaults.topAppBarColors(
        containerColor = Green,
        titleContentColor = onSurface
    ), )
}
