package com.elfeky.weather_app.presentation.ui.common

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun AnimatedLoadingText(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = TextStyle.Default,
    maxDots: Int = 3,
    animationDuration: Int = 1000
) {
    var dotCount by remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(animationDuration.toLong() / (maxDots + 1))
            dotCount = (dotCount + 1) % (maxDots + 1)
        }
    }

    Text(
        text = "$text${".".repeat(dotCount)}",
        modifier = modifier,
        style = style,
        maxLines = 1,
        fontSize = 16.sp,
        color = Color.White
    )
}