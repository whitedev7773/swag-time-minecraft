package dev.whitedev7773.swagtime.handler

import net.minecraft.client.MinecraftClient

/**
 * 공통 상태 관리 객체.
 * - 다른 코드(예: mixin, 이벤트 리스너)에서 FreezeHandler.isFrozen()를 확인해서 행동을 차단합니다.
 *
 * 주의: Kotlin object에 @JvmStatic을 붙여서 Java mixin에서 쉽게 호출할 수 있게 했습니다.
 */
object FreezeHandler {

    // Java에서 접근하기 쉽게 @JvmField 또는 @JvmStatic 사용 가능.
    // 여기서는 isFrozen() getter를 Java에서 호출하도록 @JvmStatic을 사용합니다.
    @get:JvmName("isFrozen") // Java에서 FreezeHandler.isFrozen() 로 호출 가능
    @Volatile
    var frozen: Boolean = false
        private set

    @JvmStatic
    fun freeze() {
        executeClientCommand("/tick freeze")
        frozen = true
    }

    @JvmStatic
    fun unfreeze() {
        executeClientCommand("/tick unfreeze")
        frozen = false
    }

    @JvmStatic
    fun toggle() {
        frozen = !frozen
    }

    @JvmStatic
    fun isGameFrozen(): Boolean = frozen

    fun executeClientCommand(command: String) {
        val client = MinecraftClient.getInstance()

        // 클라이언트가 서버에 접속 중인지 확인
        if (client.player != null) {
            client.player!!.networkHandler.sendChatCommand(command.removePrefix("/"))
        } else {
            println("❌ 명령 실행 불가: 클라이언트에 플레이어가 없습니다.")
        }
    }
}
