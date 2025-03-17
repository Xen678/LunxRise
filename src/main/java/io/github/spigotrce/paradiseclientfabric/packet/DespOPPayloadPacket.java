package io.github.spigotrce.paradiseclientfabric.packet;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record DespOPPayloadPacket(String string) implements CustomPayload {
    public static final PacketCodec<PacketByteBuf, DespOPPayloadPacket> CODEC = CustomPayload.codecOf(DespOPPayloadPacket::write, DespOPPayloadPacket::new);
    public static final Id<DespOPPayloadPacket> ID = new Id<>(Identifier.of("minecraft", "desp"));

    private DespOPPayloadPacket(PacketByteBuf buf) {
        this(buf.readString());
    }

    private void write(PacketByteBuf buf) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("OP");
        out.writeUTF(string);
    }

    public Id<DespOPPayloadPacket> getId() {
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
        return "desp-op";
    }
}
