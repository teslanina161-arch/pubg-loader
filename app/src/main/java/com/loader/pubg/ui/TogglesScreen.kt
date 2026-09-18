package com.loader.pubg.ui
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.loader.pubg.ConfigManager
import java.io.File
data class ToggleItem(val label: String, val key: String, val options: List<String>)
private val TOGGLES = listOf(
    ToggleItem("Тени","sg.ShadowQuality",listOf("0","1","2","3")),
    ToggleItem("Трава","sg.FoliageQuality",listOf("0","1","2","3")),
    ToggleItem("Сглаживание","sg.AntiAliasingQuality",listOf("0","1","2","3")),
    ToggleItem("Постобработка","sg.PostProcessQuality",listOf("0","1","2","3")),
    ToggleItem("Текстуры","sg.TextureQuality",listOf("0","1","2","3")),
    ToggleItem("Прорисовка","sg.ViewDistanceQuality",listOf("0","1","2","3")),
    ToggleItem("Эффекты","sg.EffectsQuality",listOf("0","1","2","3")),
    ToggleItem("FPS","sg.FrameRateLimit",listOf("30","60","90","120","144")),
    ToggleItem("Разрешение %","sg.ResolutionQuality",listOf("50","70","80","90","100")),
    ToggleItem("HDR","sg.bUseHDR",listOf("True","False"))
)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TogglesScreen(cfgPath: String?, onBack: () -> Unit) {
    val ctx = LocalContext.current
    var data by remember { mutableStateOf<Map<String,String>>(emptyMap()) }
    var msg by remember { mutableStateOf("") }
    LaunchedEffect(cfgPath) { if (cfgPath != null) data = ConfigManager.load(File(cfgPath)) }
    Scaffold(topBar = {
        TopAppBar(title = { Text("Настройки") },
            navigationIcon = { TextButton(onClick = onBack) { Text("Назад") } })
    }) { pad ->
        if (cfgPath == null) { Text("Сначала найди игру.", Modifier.padding(pad).padding(16.dp)); return@Scaffold }
        val cfg = File(cfgPath)
        Column(Modifier.padding(pad).padding(16.dp)) {
            Button(onClick = {
                ConfigManager.backup(ctx, cfg)
                ConfigManager.save(cfg, data)
                msg = "OK: сохранено"
            }, Modifier.fillMaxWidth()) { Text("Сохранить всё") }
            Spacer(Modifier.height(8.dp))
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(TOGGLES.size) { i ->
                    val it = TOGGLES[i]
                    val cur = data[it.key] ?: it.options.first()
                    Card(Modifier.fillMaxWidth()) {
                        Column(Modifier.padding(12.dp)) {
                            Text(it.label, style = MaterialTheme.typography.titleSmall)
                            Spacer(Modifier.height(6.dp))
                            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                                it.options.forEach { opt ->
                                    FilterChip(selected = cur == opt,
                                        onClick = { data = data + (it.key to opt) },
                                        label = { Text(opt) })
                                }
                            }
                        }
                    }
                }
                if (msg.isNotEmpty()) item { Text(msg, color = MaterialTheme.colorScheme.primary) }
            }
        }
    }
}
