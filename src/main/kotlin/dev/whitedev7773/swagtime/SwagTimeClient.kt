package dev.whitedev7773.swagtime

import dev.whitedev7773.swagtime.SwagTime.MOD_ID
import dev.whitedev7773.swagtime.hud.GrayOverlayHud
import dev.whitedev7773.swagtime.hud.ImageOverlayHud
import dev.whitedev7773.swagtime.handler.SoundHandler

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements

import net.minecraft.util.Identifier


class SwagTimeClient : ClientModInitializer {
    override fun onInitializeClient() {
        // 사운드 정의
        SoundHandler

        // 회색 오버레이 정의
        HudElementRegistry.attachElementBefore(
            VanillaHudElements.CROSSHAIR,
            Identifier.of(MOD_ID, "swag_time_hud_element")
        ) { context, tickCounter -> GrayOverlayHud.render(context, tickCounter) }

        // 이미지 오버레이 정의
        HudElementRegistry.attachElementBefore(
            VanillaHudElements.CROSSHAIR,
            Identifier.of("swagtime", "image_overlay"),
            ImageOverlayHud
        )
    }
}