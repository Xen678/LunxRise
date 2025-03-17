package io.github.spigotrce.paradiseclientfabric.command.impl;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import io.github.spigotrce.paradiseclientfabric.command.Command;
import net.minecraft.client.MinecraftClient;
import net.minecraft.command.CommandSource;

public class FakeGMCommand extends Command {
    /**
     * Constructor for the Command class.
     * @param minecraftClient The Minecraft client instance.
     */
    public FakeGMCommand(MinecraftClient minecraftClient) {
        super("fake-gm", "Client Side Gamemode change", minecraftClient);
    }

    /**
     * Abstract method to build the command using Brigadier's argument builder.
     *
     * @return A Brigadier literal argument builder for the command.
     */
    @Override
    public LiteralArgumentBuilder<CommandSource> build() {
        LiteralArgumentBuilder<CommandSource> node = literal(getName());
        node.executes(command -> {
            return SINGLE_SUCCESS;
        });
        return null;
    }
}
