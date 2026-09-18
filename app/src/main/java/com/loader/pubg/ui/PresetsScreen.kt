package com.loader.pubg.ui
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.loader.pubg.ConfigManager
import com.loader.pubg.Presets
import java.io.File
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PresetsScreen(cfgPath: String?, onBack: () -> Unit) {
    val ctx = LocalContext.current
    var msg by remember { mutableStateOf("") }
    Scaffold(topBar = {
        TopAppBar(
            title = { Text("Пресеты") },
            navigationIcon = { TextButton(onClick = onBack) { Text("Назад") } }
        )
    }) { pad ->
        Column(Modifier.padding(pad).padding(16.dp)) {
            if (cfgPath == null) { Text("Сначала найди игру."); return@Column }
            val cfg = File(cfgPath)
            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(Presets.DATA.keys.toList()) { presetName ->
                    Card(onClick = {
                        val b = ConfigManager.backup(ctx, cfg)
                        val data = ConfigManager.load(cfg).toMutableMap()
                        data.putAll(Presets.DATA[presetName]!!)
                        val ok = ConfigManager.save(cfg, data)
                        msg = if (ok) "OK: $presetName" else "Ошибка"
                    }, modifier = Modifier.fillMaxWidth()) {
                        Column(Modifier.padding(16.dp)) {
                            Text(presetName, style = MaterialTheme.typography.titleMedium)
                            Text("Тапни для применения", style = MaterialTheme.typography.bodySmall)
                        }
                    }
                }
                if (msg.isNotEmpty()) item {
                    Card(Modifier.fillMaxWidth()) {
                        Text(msg, Modifier.padding(12.dp), color = MaterialTheme.colorScheme.primary)
                    }
                }
            }
        }
    }
}
