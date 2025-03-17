package io.github.spigotrce.paradiseclientfabric.packet;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

import java.util.UUID;

public record InterChatPayloadPacket(String uuid, String command) implements CustomPayload {
    public static final PacketCodec<PacketByteBuf, InterChatPayloadPacket> CODEC = CustomPayload.codecOf(InterChatPayloadPacket::write, InterChatPayloadPacket::new);
    public static final CustomPayload.Id<InterChatPayloadPacket> ID = new CustomPayload.Id<>(Identifier.of("interchat", "main"));

    private InterChatPayloadPacket(PacketByteBuf buf) {
        this(buf.readString(), buf.readString());
    }

    private void write(PacketByteBuf buf) {
        buf.writeInt(0x15);
        buf.writeUuid(UUID.fromString(uuid));
        buf.writeString(command);
    }

    public CustomPayload.Id<InterChatPayloadPacket> getId() {
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
        return "interchat";
    }
}
