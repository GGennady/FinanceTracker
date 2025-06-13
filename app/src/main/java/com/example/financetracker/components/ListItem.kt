package com.example.financetracker.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.financetracker.R
import com.example.financetracker.ui.theme.Black
import com.example.financetracker.ui.theme.Subtitile
import com.example.financetracker.ui.theme.Typography
import com.example.financetracker.ui.theme.outlineVariant

@Composable
fun ListItem(
    modifier: Modifier = Modifier,
    emoji: String? = null,
    title: String,
    subtitle: String? = null,
    contentUpper: String? = null,
    contentLower: String? = null,
    icon: Painter? = null,
    showDivider: Boolean = false,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {

            // emoji
            if (emoji != null) {
                Text(
                    text = emoji,
                    modifier = Modifier
                        .padding(end = 16.dp),
                )
            }

            // title & subtitle
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 12.dp),
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = title,
                    style = Typography.bodyMedium,
                    color = Black,
                )
                if (subtitle != null) {
                    Text(
                        text = subtitle,
                        style = Typography.bodySmall,
                        color = Subtitile,
                    )
                }
            }

            // contentUpper & contentLower
            if (contentUpper != null || contentLower != null) {
                Column(
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(end = if (icon != null) 16.dp else 0.dp),
                ) {
                    contentUpper?.let {
                        Text(
                            text = it,
                            style = Typography.bodyMedium,
                        )
                    }
                    contentLower?.let {
                        Text(
                            text = it,
                            style = Typography.bodySmall,
                            color = Subtitile,
                        )
                    }
                }
            }

            // icon
            if (icon != null) {
                Icon(
                    painter = icon,
                    contentDescription = "Icon",
                )
            }
        }

        if (showDivider) {
            HorizontalDivider(
                thickness = 1.dp,
                color = outlineVariant
            )
        }
    }
}

@Composable
@Preview
private fun ListItemPreview() =
    ListItem(
        modifier = Modifier,
        emoji = "📚",
        title = "Books",
        subtitle = "Reading list",
        contentUpper = "12 items",
        contentLower = "Updated today",
        icon = painterResource(id = R.drawable.ic_fab_plus),
        showDivider = true
    )