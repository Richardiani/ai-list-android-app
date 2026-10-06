package com.example.ailist.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = Color(0xFF6750A4),
    onPrimary = Color.White,
    secondary = Color(0xFF625B71),
    onSecondary = Color.White,
    background = Color(0xFFF8F4FF),
    surface = Color.White,
    onSurface = Color(0xFF1D1B20),
    error = Color(0xFFB3261E),
    primaryContainer = Color(0xFFEADDFF),
    surfaceVariant = Color(0xFFE7E0EC)
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFFD0BCFF),
    onPrimary = Color(0xFF381E72),
    secondary = Color(0xFFCCC2DC),
    onSecondary = Color(0xFF332D41),
    background = Color(0xFF1C1B1F),
    surface = Color(0xFF2B2930),
    onSurface = Color(0xFFE6E1E5),
    error = Color(0xFFF2B8B5),
    primaryContainer = Color(0xFF4F378B),
    surfaceVariant = Color(0xFF49454F)
)

@Composable
fun AIListTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColors,
        content = content
    )
}
