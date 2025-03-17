package io.github.spigotrce.paradiseclientfabric.command.impl;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import io.github.spigotrce.paradiseclientfabric.Helper;
import io.github.spigotrce.paradiseclientfabric.command.Command;
import net.minecraft.client.MinecraftClient;
import net.minecraft.command.CommandSource;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

/**
 * This class represents a command that copies a specific broadcast message to the clipboard.
 * The command is part of the ParadiseClientFabric mod for Minecraft.
 *
 * @author SpigotRCE
 * @version 1.0
 */
public class CopyCommand extends Command {

    /**
     * Constructs a new CopyCommand instance.
     *
     * @param minecraftClient The Minecraft client instance.
     */
    public CopyCommand(MinecraftClient minecraftClient) {
        super("copy", "Copies the broadcast of Lunx", minecraftClient);
    }

    /**
     * Builds the command structure using Brigadier's LiteralArgumentBuilder.
     * The command has two sub-commands: "tellraw" and the default command.
     *
     * @return The built command structure.
     */
    @Override
    public LiteralArgumentBuilder<CommandSource> build() {
        return
                literal(getName())
                        .then(literal("tellraw")
                                .executes((context) -> {
                                    // Copies a specific tellraw message to the clipboard.
                                    StringSelection stringSelection = new StringSelection("tellraw @a /tellraw @a [\"\",{\"text\":\"GRIE\",\"color\":\"#FF6C00\"},{\"text\":\"FED \",\"color\":\"#F7A200\"},{\"text\":\"BY\",\"color\":\"#56FF00\"},{\"text\":\" LUNX\",\"color\":\"#8FB9FF\"},{\"text\":\"&\",\"color\":\"#FF0003\"},{\"text\":\"DEEPS\",\"color\":\"#F2FFBA\"},{\"text\":\" | \"},{\"text\":\"H\",\"color\":\"#65A2FF\"},{\"text\":\"O\",\"color\":\"#47D2FF\"},{\"text\":\"LL\",\"color\":\"#17FFD3\"},{\"text\":\"Y\",\"color\":\"#37FF91\"},{\"text\":\"CLIENT \",\"color\":\"#00F88F\"},{\"text\":\"| \"},{\"text\":\"dsc.gg/hollyclient\",\"color\":\"#8FFFE9\"}]");
                                    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                                    clipboard.setContents(stringSelection, null);
                                    Helper.printChatMessage("Lunx's tellraw has been copied to your clipboard.");
                                    return SINGLE_SUCCESS;
                                }))
                        .executes((context) -> {
                            // Copies a specific formatted message to the clipboard.
                            StringSelection stringSelection = new StringSelection("&aServer hacked by&b&l https://youtube.com/@SpigotRCE");
                            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                            clipboard.setContents(stringSelection, null);
                            Helper.printChatMessage("SpigotRCE's broadcast has been copied to your clipboard.");
                            return SINGLE_SUCCESS;
                        });
    }
}
