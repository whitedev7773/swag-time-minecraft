package dev.whitedev7773.swagtime.handler

import dev.whitedev7773.swagtime.SwagTime.MOD_ID
import dev.whitedev7773.swagtime.hud.GrayOverlayHud
import dev.whitedev7773.swagtime.hud.ImageOverlayHud

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents
import net.fabricmc.fabric.api.event.player.UseBlockCallback
import net.fabricmc.fabric.api.event.player.UseItemCallback
import net.fabricmc.fabric.api.event.player.UseEntityCallback
import net.fabricmc.fabric.api.event.player.AttackEntityCallback
import net.minecraft.util.ActionResult

import org.slf4j.LoggerFactory

class PlayerEventHandler {
    private val logger = LoggerFactory.getLogger(MOD_ID)

    fun freeze() {
        GrayOverlayHud.visible = true
        ImageOverlayHud.visible = true
        SoundHandler.playRandom()
        FreezeHandler.freeze()

        // 2초후 오버레이와 사운드 중지
        Thread {
            Thread.sleep(2000)
            GrayOverlayHud.visible = false
            ImageOverlayHud.visible = false
            SoundHandler.stopAll()
            FreezeHandler.unfreeze()
        }.start()
    }

    fun registerEvents() {
        // 블록을 부술 때 이벤트 감지
        PlayerBlockBreakEvents.AFTER.register { world, player, pos, state, blockEntity ->
            if (!world.isClient) {
                logger.info("${player.name.string} broke a block at $pos")
                freeze()
            }
        }

        // 블록과 상호작용할 때 이벤트 감지
        UseBlockCallback.EVENT.register { player, world, hand, hitResult ->
            if (!world.isClient) {
                logger.info("${player.name.string} interacted with a block at ${hitResult.blockPos}")
                freeze()
            }
            ActionResult.PASS
        }

        // 아이템을 사용할 때 이벤트 감지
        UseItemCallback.EVENT.register { player, world, hand ->
            if (!world.isClient) {
                logger.info("${player.name.string} used an item: ${player.getStackInHand(hand).item}")
                freeze()
            }
            ActionResult.PASS
        }

        // 엔티티와 상호작용할 때 이벤트 감지
        UseEntityCallback.EVENT.register { player, world, hand, entity, hitResult ->
            if (!world.isClient) {
                logger.info("${player.name.string} interacted with entity: ${entity.name.string}")
                freeze()
            }
            ActionResult.PASS
        }

        // 엔티티를 공격할 때 이벤트 감지
        AttackEntityCallback.EVENT.register { player, world, hand, entity, hitResult ->
            if (!world.isClient) {
                logger.info("${player.name.string} attacked ${entity.name.string}")
                freeze()
            }
            ActionResult.PASS
        }
    }
}