package com.loader.pubg.ui
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
private val Scheme = darkColorScheme(
    primary = Color(0xFFFF6B00), onPrimary = Color.Black,
    secondary = Color(0xFFFFB800), onSecondary = Color.Black,
    background = Color(0xFF0E0E0E), surface = Color(0xFF1A1A1A),
    onBackground = Color.White, onSurface = Color.White,
    surfaceVariant = Color(0xFF2A2A2A), onSurfaceVariant = Color(0xFFCCCCCC)
)
@Composable
fun PubgTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = Scheme, content = content)
}
