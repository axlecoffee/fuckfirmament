package coffee.axle.antifirm.mixin;

import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.common.ServerboundCustomPayloadPacket;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import coffee.axle.antifirm.FuckFirmament;
import io.netty.channel.ChannelFutureListener;

@Mixin(Connection.class)
public class ConnectionMixin {

    private static final ResourceLocation FIRMAMENT_MOD_LIST = ResourceLocation.parse("firmament:mod_list");

    @Inject(method = "sendPacket", at = @At("HEAD"), cancellable = true)
    private void antifirm$blockModAnnouncer(Packet<?> packet, ChannelFutureListener callback, boolean flush,
            CallbackInfo ci) {
        if (packet instanceof ServerboundCustomPayloadPacket customPayloadPacket) {
            CustomPacketPayload payload = customPayloadPacket.payload();
            CustomPacketPayload.Type<?> type = payload.type();

            if (type != null && FIRMAMENT_MOD_LIST.equals(type.id())) {
                FuckFirmament.LOGGER.info("Blocked Firmament mod announcer packet (evil)");
                ci.cancel();
            }
        }
    }
}
