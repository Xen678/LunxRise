package io.github.spigotrce.paradiseclientfabric.command.impl;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import io.github.spigotrce.paradiseclientfabric.Helper;
import io.github.spigotrce.paradiseclientfabric.ParadiseClient_Fabric;
import io.github.spigotrce.paradiseclientfabric.command.Command;
import net.minecraft.client.MinecraftClient;
import net.minecraft.command.CommandSource;

/**
 * Represents a command that toggles the display of server IP on the HUD.
 *
 * @author SpigotRCE
 * @since 1.4
 */
public class ScreenShareCommand extends Command {

    /**
     * Constructs a new instance of {@link ScreenShareCommand}.
     *
     * @param minecraftClient The Minecraft client instance.
     */
    public ScreenShareCommand(MinecraftClient minecraftClient) {
        super("server-ip-hide", "Toggles IP displayed on HUD", minecraftClient);
    }

    /**
     * Builds the command structure using Brigadier's {@link LiteralArgumentBuilder}.
     *
     * @return The built command structure.
     */
    @Override
    public LiteralArgumentBuilder<CommandSource> build() {
        return literal(getName());
    }
}

