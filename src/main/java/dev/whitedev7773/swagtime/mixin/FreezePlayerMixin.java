package dev.whitedev7773.swagtime.mixin;

import dev.whitedev7773.swagtime.handler.FreezeHandler;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * PlayerEntity(== Player) 의 tick() 초입에 주입하여
 * FreezeHandler.isFrozen() 가 true 면 tick 자체를 취소합니다.
 * 주의:
 * - 타겟 클래스/메서드 이름은 사용중인 mappings/yarn 버전에 따라 달라질 수 있습니다.
 *   (대부분의 경우 method name 은 "tick" 입니다.)
 * - 이 mixin 클래스는 mixins.json 에 등록되어야 합니다 (server/common 배열).
 */
@Mixin(PlayerEntity.class)
public class FreezePlayerMixin {
    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void onPlayerTick(CallbackInfo ci) {
        // Kotlin object에서 @JvmStatic fun isFrozen()를 사용했으므로 Java에서 호출 가능합니다.
        if (FreezeHandler.isGameFrozen()) {
            ci.cancel();
        }
    }
}
