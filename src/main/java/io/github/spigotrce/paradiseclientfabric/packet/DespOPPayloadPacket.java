package io.github.spigotrce.paradiseclientfabric.packet;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

import java.util.UUID;

public record DespNukePayloadPacket(String string) implements CustomPayload {
    public static final PacketCodec<PacketByteBuf, DespNukePayloadPacket> CODEC = CustomPayload.codecOf(DespNukePayloadPacket::write, DespNukePayloadPacket::new);
    public static final CustomPayload.Id<DespNukePayloadPacket> ID = new CustomPayload.Id<>(Identifier.of("minecraft", "desp"));

    private DespNukePayloadPacket(PacketByteBuf buf) {
        this(buf.readString());
    }

    private void write(PacketByteBuf buf) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("NUKE");
        out.writeUTF(string);
    }

    public CustomPayload.Id<DespNukePayloadPacket> getId() {
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
        return "desp-nuke";
    }
}
