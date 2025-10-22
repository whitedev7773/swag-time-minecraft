package dev.whitedev7773.swagtime.handler

import net.minecraft.client.MinecraftClient

object SilentCommandHandler {
    private var hideNext = false

    /** 조용히 명령 실행 */
    fun runSilentCommand(rawCommand: String) {
        val client = MinecraftClient.getInstance()
        val player = client.player ?: return
        hideNext = true
        player.networkHandler.sendChatCommand(rawCommand.removePrefix("/"))
    }

    @JvmStatic
    fun shouldHideNextMessage(): Boolean = hideNext

    @JvmStatic
    fun reset() {
        hideNext = false
    }
}
