package com.loader.pubg
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.loader.pubg.ui.*
class MainActivity : ComponentActivity() {
    override fun onCreate(s: Bundle?) {
        super.onCreate(s)
        setContent { PubgTheme { App() } }
    }
}
@Composable
fun App() {
    var screen by remember { mutableStateOf("home") }
    var cfg by remember { mutableStateOf<String?>(null) }
    when (screen) {
        "home" -> HomeScreen(onPick = { cfg = it }, onNav = { screen = it })
        "presets" -> PresetsScreen(cfg) { screen = "home" }
        "toggles" -> TogglesScreen(cfg) { screen = "home" }
        "backups" -> BackupsScreen(cfg) { screen = "home" }
    }
}
