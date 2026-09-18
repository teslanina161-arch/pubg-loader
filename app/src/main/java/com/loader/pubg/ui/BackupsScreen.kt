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
import java.io.File
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BackupsScreen(cfgPath: String?, onBack: () -> Unit) {
    val ctx = LocalContext.current
    var list by remember { mutableStateOf<List<File>>(emptyList()) }
    var msg by remember { mutableStateOf("") }
    LaunchedEffect(cfgPath) { if (cfgPath != null) list = ConfigManager.listBackups(ctx, File(cfgPath).name) }
    Scaffold(topBar = {
        TopAppBar(title = { Text("Бэкапы") },
            navigationIcon = { TextButton(onClick = onBack) { Text("Назад") } })
    }) { pad ->
        Column(Modifier.padding(pad).padding(16.dp)) {
            if (cfgPath == null) { Text("Сначала найди игру."); return@Column }
            val cfg = File(cfgPath)
            if (list.isEmpty()) Text("Бэкапов пока нет.")
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(list) { f ->
                    Card(Modifier.fillMaxWidth()) {
                        Row(Modifier.padding(12.dp).fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween) {
                            Column(Modifier.weight(1f)) {
                                Text(f.name, style = MaterialTheme.typography.bodyMedium)
                                Text("${f.length()} B", style = MaterialTheme.typography.bodySmall)
                            }
                            TextButton(onClick = {
                                val ok = ConfigManager.restore(f, cfg)
                                msg = if (ok) "OK" else "Ошибка"
                            }) { Text("Восст.") }
                            TextButton(onClick = {
                                f.delete(); list = ConfigManager.listBackups(ctx, cfg.name)
                            }) { Text("Удалить") }
                        }
                    }
                }
                if (msg.isNotEmpty()) item { Text(msg, color = MaterialTheme.colorScheme.primary) }
            }
        }
    }
}
