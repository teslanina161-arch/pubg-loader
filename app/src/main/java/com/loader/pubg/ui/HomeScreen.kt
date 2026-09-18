package com.loader.pubg.ui
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.loader.pubg.GameFinder
import java.io.File
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onPick: (String?) -> Unit, onNav: (String) -> Unit) {
    var found by remember { mutableStateOf<List<Triple<String,String,File>>>(emptyList()) }
    var status by remember { mutableStateOf("Нажми Найти игру") }
    Scaffold(topBar = { TopAppBar(title = { Text("PUBG Loader") }) }) { pad ->
        Column(
            Modifier.padding(pad).padding(16.dp).fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Card(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(16.dp)) {
                    Text("Статус", style = MaterialTheme.typography.titleMedium)
                    Spacer(Modifier.height(4.dp))
                    Text(status, style = MaterialTheme.typography.bodyMedium)
                }
            }
            Button(onClick = {
                val list = mutableListOf<Triple<String,String,File>>()
                for ((name, pkg) in GameFinder.PACKAGES) {
                    GameFinder.findGame(pkg)?.let { list.add(Triple(name, pkg, it)) }
                }
                found = list
                status = if (list.isEmpty()) "PUBG не найдена" else "Найдено: ${list.size}"
            }, modifier = Modifier.fillMaxWidth()) { Text("Найти игру") }
            LazyColumn(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(found) { item ->
                    val name = item.first
                    val pkg = item.second
                    val dir = item.third
                    Card(onClick = {
                        val cfg = GameFinder.findCfg(dir)
                        onPick(cfg?.absolutePath)
                        status = if (cfg != null) "OK: ${cfg.name}" else "Конфиг не найден"
                    }, modifier = Modifier.fillMaxWidth()) {
                        Column(Modifier.padding(16.dp)) {
                            Text(name, style = MaterialTheme.typography.titleMedium)
                            Text(pkg, style = MaterialTheme.typography.bodySmall)
                            Text(dir.absolutePath, style = MaterialTheme.typography.bodySmall)
                        }
                    }
                }
            }
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedButton(onClick = { onNav("presets") }, Modifier.weight(1f)) { Text("Пресеты") }
                OutlinedButton(onClick = { onNav("toggles") }, Modifier.weight(1f)) { Text("Настр.") }
                OutlinedButton(onClick = { onNav("backups") }, Modifier.weight(1f)) { Text("Бэкапы") }
            }
        }
    }
}
