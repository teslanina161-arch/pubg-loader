package com.loader.pubg
object Presets {
    val DATA: Map<String, Map<String,String>> = linkedMapOf(
        "Smooth 60 FPS" to mapOf(
            "sg.ResolutionQuality" to "70","sg.ViewDistanceQuality" to "1",
            "sg.AntiAliasingQuality" to "0","sg.ShadowQuality" to "0",
            "sg.PostProcessQuality" to "0","sg.TextureQuality" to "1",
            "sg.EffectsQuality" to "0","sg.FoliageQuality" to "0",
            "sg.FrameRateLimit" to "60","sg.bUseHDR" to "False"),
        "Balanced 90 FPS" to mapOf(
            "sg.ResolutionQuality" to "90","sg.ViewDistanceQuality" to "2",
            "sg.AntiAliasingQuality" to "1","sg.ShadowQuality" to "1",
            "sg.PostProcessQuality" to "1","sg.TextureQuality" to "2",
            "sg.EffectsQuality" to "1","sg.FoliageQuality" to "1",
            "sg.FrameRateLimit" to "90","sg.bUseHDR" to "True"),
        "HD 120 FPS" to mapOf(
            "sg.ResolutionQuality" to "100","sg.ViewDistanceQuality" to "3",
            "sg.AntiAliasingQuality" to "2","sg.ShadowQuality" to "2",
            "sg.PostProcessQuality" to "2","sg.TextureQuality" to "3",
            "sg.EffectsQuality" to "2","sg.FoliageQuality" to "2",
            "sg.FrameRateLimit" to "120","sg.bUseHDR" to "True"),
        "Ultra" to mapOf(
            "sg.ResolutionQuality" to "100","sg.ViewDistanceQuality" to "3",
            "sg.AntiAliasingQuality" to "3","sg.ShadowQuality" to "3",
            "sg.PostProcessQuality" to "3","sg.TextureQuality" to "3",
            "sg.EffectsQuality" to "3","sg.FoliageQuality" to "3",
            "sg.FrameRateLimit" to "120","sg.bUseHDR" to "True")
    )
}
