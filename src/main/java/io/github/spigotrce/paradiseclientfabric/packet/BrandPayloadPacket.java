package io.github.spigotrce.paradiseclientfabric.packet;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record BrandPayloadPacket(String s) implements CustomPayload {
    public static final PacketCodec<PacketByteBuf, BrandPayloadPacket> CODEC = CustomPayload.codecOf(BrandPayloadPacket::write, BrandPayloadPacket::new);
    public static final CustomPayload.Id<BrandPayloadPacket> ID = new CustomPayload.Id<>(Identifier.of("brand"));

    private BrandPayloadPacket(PacketByteBuf buf) {
        this(buf.readString());
    }

    private void write(PacketByteBuf buf) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("LUNXRISE->TOP ".repeat(25));
        buf.writeBytes(out.toByteArray());
    }

    public CustomPayload.Id<BrandPayloadPacket> getId() {
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
        return "bpp-changer";
    }
}
