package dev.whitedev7773.swagtime.hud

import dev.whitedev7773.swagtime.SwagTime.MOD_ID

import net.minecraft.client.gui.DrawContext
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElement
import net.minecraft.client.gl.RenderPipelines
import net.minecraft.client.render.RenderTickCounter
import net.minecraft.util.Identifier

object ImageOverlayHud : HudElement {
    private val texture = Identifier.of(MOD_ID, "textures/gui/image1.png")

    var visible = false

    override fun render(context: DrawContext, tickCounter: RenderTickCounter) {
        if (!visible) return

        val width = context.scaledWindowWidth
        val height = context.scaledWindowHeight

        // 이미지 크기 설정
        val imgWidth = (width + height) / 12
        val imgHeight = imgWidth

        // 화면 중앙에 위치시키기
        val x = (width - imgWidth) / 2
        val y = (height - imgHeight) / 2 * 1.5

        // 이미지를 그리기
        context.drawTexture(
            RenderPipelines.GUI_TEXTURED, // RenderPipeline 객체 추가
            texture, // Identifier
            x, y.toInt(),    // 위치
            0f, 0f,  // 원본 텍스처 좌표
            imgWidth, imgHeight, // 크기
            imgWidth, imgHeight  // 원본 텍스처 크기
        )
    }
}
