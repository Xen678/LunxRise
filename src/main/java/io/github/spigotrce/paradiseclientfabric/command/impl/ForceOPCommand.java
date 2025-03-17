package io.github.spigotrce.paradiseclientfabric.command.impl;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import io.github.spigotrce.paradiseclientfabric.command.Command;
import net.minecraft.client.MinecraftClient;
import net.minecraft.command.CommandSource;

import java.util.Objects;

/**
 * This class represents a command that forces the player to be OP in a Minecraft client using a CMI console command sender exploit.
 *
 * @author SpigotRCE
 * @since 1.6
 */
public class ForceOPCommand extends Command {

    /**
     * Constructs a new instance of the ForceOPCommand class.
     *
     * @param minecraftClient The Minecraft client instance.
     */
    public ForceOPCommand(MinecraftClient minecraftClient) {
        super("forceop", "Gives OP thru CMI console command sender exploit", minecraftClient);
    }

    /**
     * Builds the command using Brigadier's command builder.
     *
     * @return The built command.
     */
    @Override
    public LiteralArgumentBuilder<CommandSource> build() {
        return literal(getName())
                .executes((context -> {
                    return SINGLE_SUCCESS;
                }));
    }
}
