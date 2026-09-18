package com.loader.pubg
import android.content.Context
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
object ConfigManager {
    fun load(cfg: File): MutableMap<String, String> {
        val map = mutableMapOf<String, String>()
        if (!cfg.exists()) return map
        cfg.forEachLine { line ->
            val s = line.trim()
            if (s.isEmpty() || s.startsWith(";") || s.startsWith("#")
                || s.startsWith("[") || s.startsWith("//")) return@forEachLine
            if (s.contains("=")) {
                val parts = s.split("=", limit = 2)
                map[parts[0].trim()] = parts[1].trim()
            }
        }
        return map
    }
    fun save(cfg: File, data: Map<String, String>): Boolean = try {
        cfg.writeText(data.entries.joinToString("\n") { "${it.key}=${it.value}" } + "\n")
        true
    } catch (e: Exception) { false }
    fun backup(ctx: Context, cfg: File): File? = try {
        val dir = File(ctx.filesDir, "backups").apply { mkdirs() }
        val ts = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
        val dst = File(dir, "${cfg.name}.$ts.bak")
        cfg.copyTo(dst, overwrite = true)
        dst
    } catch (e: Exception) { null }
    fun listBackups(ctx: Context, cfgName: String): List<File> {
        val dir = File(ctx.filesDir, "backups")
        if (!dir.exists()) return emptyList()
        return dir.listFiles { f -> f.name.startsWith("$cfgName.") }
            ?.sortedByDescending { it.name } ?: emptyList()
    }
    fun restore(backup: File, target: File): Boolean = try {
        backup.copyTo(target, overwrite = true)
        true
    } catch (e: Exception) { false }
}
