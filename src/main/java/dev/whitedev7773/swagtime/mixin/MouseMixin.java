package dev.whitedev7773.swagtime.mixin;

import dev.whitedev7773.swagtime.handler.FreezeHandler;

import net.minecraft.client.Mouse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * 클라이언트의 Mouse 입력 전체를 차단하는 Mixin.
 * - 회전(updateMouse)
 * - 스크롤(onMouseScroll)
 */
@Mixin(Mouse.class)
public class MouseMixin {

    /** 마우스 회전(시점 이동) */
    @Inject(method = "updateMouse", at = @At("HEAD"), cancellable = true)
    private void onMouseUpdate(CallbackInfo ci) {
        if (FreezeHandler.isGameFrozen()) {
            ci.cancel();
        }
    }

    /** 스크롤 (휠 위/아래, 좌/우 포함) */
    @Inject(method = "onMouseScroll", at = @At("HEAD"), cancellable = true)
    private void onMouseScroll(long window, double horizontal, double vertical, CallbackInfo ci) {
        if (FreezeHandler.isGameFrozen()) {
            ci.cancel();
        }
    }

//    @Inject(method = "onMouseButton", at = @At("HEAD"), cancellable = true)
//    private void onMouseButton(long window, MouseInput input, int action, CallbackInfo ci) {
//        if (FreezeHandler.isGameFrozen()) {
//            ci.cancel();
//        }
//    }
}