package dev.whitedev7773.swagtime.hud

import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElement
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.render.RenderTickCounter

object GrayOverlayHud : HudElement {
    var visible = false

    override fun render(context: DrawContext, tickCounter: RenderTickCounter) {
        if (!visible) return
        renderGray(context)
    }

    private fun renderGray(context: DrawContext) {
        val windowSizeX = context.scaledWindowWidth
        val windowSizeY = context.scaledWindowHeight
        val color = 0x50ACACAC
        context.fill(0, 0, windowSizeX, windowSizeY, color)
    }
}