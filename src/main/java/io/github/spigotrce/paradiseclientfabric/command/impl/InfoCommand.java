package io.github.spigotrce.paradiseclientfabric.command.impl;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import io.github.spigotrce.paradiseclientfabric.Helper;
import io.github.spigotrce.paradiseclientfabric.ParadiseClient_Fabric;
import io.github.spigotrce.paradiseclientfabric.command.Command;
import net.minecraft.client.MinecraftClient;
import net.minecraft.command.CommandSource;

public class InfoCommand extends Command {
    /**
     * Constructor for the Command class.
     *
     * @param minecraftClient The Minecraft client instance.
     */
    public InfoCommand( MinecraftClient minecraftClient) {
        super("info", "Gives some Info about LunxRise", minecraftClient);
    }

    /**
     * Abstract method to build the command using Brigadier's argument builder.
     *
     * @return A Brigadier literal argument builder for the command.
     */
    @Override
    public LiteralArgumentBuilder<CommandSource> build() {
        LiteralArgumentBuilder<CommandSource> node = literal(getName());

        node.executes((c) -> {
            Helper.printChatMessage("&aLunxRise Fork of &eParadiseClient &acoded by &cLunx");
            return SINGLE_SUCCESS;
        });

        return node;
    }
}
