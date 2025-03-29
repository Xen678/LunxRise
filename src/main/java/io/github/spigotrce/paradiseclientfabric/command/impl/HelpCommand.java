package io.github.spigotrce.paradiseclientfabric.command.impl;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import io.github.spigotrce.paradiseclientfabric.Helper;
import io.github.spigotrce.paradiseclientfabric.command.Command;
import net.minecraft.client.MinecraftClient;
import net.minecraft.command.CommandSource;

/**
 * Represents a command that displays help information for other commands.
 *
 * @author SpigotRCE
 * @since 1.4
 */
public class HelpCommand extends Command {

    /**
     * Constructs a new instance of {@link HelpCommand}.
     *
     * @param minecraftClient The Minecraft client instance.
     */
    public HelpCommand(MinecraftClient minecraftClient) {
        super("help", "Shows help page", minecraftClient);
    }

    /**
     * Builds the command structure using Brigadier's {@link LiteralArgumentBuilder}.
     *
     * @return The built command structure.
     */
    @Override
    public LiteralArgumentBuilder<CommandSource> build() {
        LiteralArgumentBuilder<CommandSource> node = literal(getName());

        return node;
    }

    private void printDash() {
        double count = MinecraftClient.getInstance().options.getChatWidth().getValue() * 360 / (MinecraftClient.getInstance().textRenderer.getWidth("-") + 1);
        Helper.printChatMessage("§a" + "-".repeat(((int) count)), false);
    }
}
