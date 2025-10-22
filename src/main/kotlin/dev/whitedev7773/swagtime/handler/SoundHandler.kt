package dev.whitedev7773.swagtime.handler

import dev.whitedev7773.swagtime.SwagTime
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.MinecraftClient
import net.minecraft.client.sound.PositionedSoundInstance
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.sound.SoundEvent
import net.minecraft.util.Identifier

@Environment(EnvType.CLIENT)
object SoundHandler {
    private val client get() = MinecraftClient.getInstance()

    // Identifier
    private val MUSIC1_ID = Identifier.of(SwagTime.MOD_ID, "music1")
    private val MUSIC2_ID = Identifier.of(SwagTime.MOD_ID, "music2")
    private val MUSIC3_ID = Identifier.of(SwagTime.MOD_ID, "music3")
    private val MUSIC4_ID = Identifier.of(SwagTime.MOD_ID, "music4")

    // SoundEvent 등록
    val MUSIC1_EVENT: SoundEvent = Registry.register(Registries.SOUND_EVENT, MUSIC1_ID, SoundEvent.of(MUSIC1_ID))
    val MUSIC2_EVENT: SoundEvent = Registry.register(Registries.SOUND_EVENT, MUSIC2_ID, SoundEvent.of(MUSIC2_ID))
    val MUSIC3_EVENT: SoundEvent = Registry.register(Registries.SOUND_EVENT, MUSIC3_ID, SoundEvent.of(MUSIC3_ID))
    val MUSIC4_EVENT: SoundEvent = Registry.register(Registries.SOUND_EVENT, MUSIC4_ID, SoundEvent.of(MUSIC4_ID))

    // 맵으로 정리
    private val sounds = mapOf(
        "music1" to MUSIC1_EVENT,
        "music2" to MUSIC2_EVENT,
        "music3" to MUSIC3_EVENT,
        "music4" to MUSIC4_EVENT
    )

    fun play(name: String, volume: Float = 1.0f, pitch: Float = 1.0f) {
        val sound = sounds[name] ?: return
        client.soundManager.play(PositionedSoundInstance.master(sound, pitch, volume/2))
    }

    fun playRandom() {
        val randomKey = sounds.keys.random()
        play(randomKey)
    }

    fun stopAll() {
        client.soundManager.stopAll()
    }
}