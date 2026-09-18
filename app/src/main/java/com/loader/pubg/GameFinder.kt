package com.loader.pubg
import android.os.Environment
import java.io.File
object GameFinder {
    val PACKAGES = linkedMapOf(
        "PUBG Mobile Global" to "com.tencent.ig",
        "PUBG Mobile KR" to "com.rekoo.pubgm",
        "PUBG Mobile VN" to "com.vng.pubgmobile",
        "BGMI (India)" to "com.pubg.imobile",
        "PUBG TW" to "com.pubg.tw"
    )
    private val CFG_RELS = listOf(
        "files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/Config/Android/UserSettings.ini",
        "files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/Config/Android/UserCustom.ini",
        "files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/Config/Android/Active.sav",
        "files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/SaveGames/Active.sav"
    )
    fun findGame(pkg: String): File? {
        val root = Environment.getExternalStorageDirectory()
        return listOf(
            File(root, "Android/data/$pkg"),
            File(root, "Android/media/$pkg")
        ).firstOrNull { it.exists() }
    }
    fun findCfg(gameDir: File): File? {
        for (rel in CFG_RELS) {
            val f = File(gameDir, rel)
            if (f.exists()) return f
        }
        return gameDir.walkTopDown()
            .firstOrNull { it.name in listOf("UserSettings.ini","UserCustom.ini","Active.sav") }
    }
}
