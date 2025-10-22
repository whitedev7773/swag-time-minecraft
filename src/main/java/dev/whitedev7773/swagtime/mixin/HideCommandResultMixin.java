package dev.whitedev7773.swagtime.mixin;

import dev.whitedev7773.swagtime.handler.SilentCommandHandler;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.s2c.play.GameMessageS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * 서버에서 보낸 채팅/시스템 메시지를 가로채서
 * 특정 명령 결과일 경우 표시하지 않게 함.
 */
@Mixin(ClientPlayNetworkHandler.class)
public class HideCommandResultMixin {
    @Inject(method = "onGameMessage", at = @At("HEAD"), cancellable = true)
    private void onGameMessage(GameMessageS2CPacket packet, CallbackInfo ci) {
        if (SilentCommandHandler.shouldHideNextMessage()) {
            ci.cancel(); // 메시지 표시 취소
            SilentCommandHandler.reset();
        }
    }
}
