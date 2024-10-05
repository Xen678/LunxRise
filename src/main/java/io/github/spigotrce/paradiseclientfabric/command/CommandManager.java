package io.github.spigotrce.paradiseclientfabric.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import io.github.spigotrce.eventbus.event.EventHandler;
import io.github.spigotrce.eventbus.event.listener.Listener;
import io.github.spigotrce.paradiseclientfabric.Constants;
import io.github.spigotrce.paradiseclientfabric.Helper;
import io.github.spigotrce.paradiseclientfabric.ParadiseClient_Fabric;
import io.github.spigotrce.paradiseclientfabric.command.impl.*;
import io.github.spigotrce.paradiseclientfabric.event.chat.ChatPreEvent;
import net.minecraft.client.MinecraftClient;
import net.minecraft.command.CommandSource;

import java.util.ArrayList;

/**
 * Manages and registers commands for the Paradise Client Fabric mod.
 */
public class CommandManager implements Listener {

    /**
     * The command dispatcher used to register commands.
     */
    public final CommandDispatcher<CommandSource> DISPATCHER = new CommandDispatcher<>();

    /**
     * A list of all registered commands.
     */
    private final ArrayList<Command> commands = new ArrayList<>();

    /**
     * The {@link MinecraftClient} instance.
     */
    private final MinecraftClient minecraftClient;

    /**
     * The command dispatcher prefix used to execute commands.
     */
    public final String prefix = ".";


    /**
     * Constructs a new CommandManager instance and registers all commands.
     *
     */
    public CommandManager(MinecraftClient minecraftClient) {
        this.minecraftClient = minecraftClient;
    }

    public void init() {
        register(new CopyCommand(minecraftClient));
        register(new ExploitCommand(minecraftClient));
        register(new HelpCommand(minecraftClient));
        register(new ForceOPCommand(minecraftClient));
        register(new GriefCommand(minecraftClient));
        register(new ScreenShareCommand(minecraftClient));
        register(new SpamCommand(minecraftClient));
        register(new PlayersCommand(minecraftClient));
        register(new SoundCommand(minecraftClient));
        register(new MotionBlurCommand(minecraftClient));
        register(new ToggleTABCommand(minecraftClient));

        DISPATCHER.register(
                new ParadiseCommand(minecraftClient).build()
        );
    }

    /**
     * Registers a command with the command dispatcher.
     *
     * @param command The command to register.
     */
    public void register(Command command) {
        this.commands.add(command);
    }

    /**
     * Returns a list of all registered commands.
     *
     * @return The list of commands.
     */
    public ArrayList<Command> getCommands() {
        return this.commands;
    }

    /**
     * Returns the command with the specified alias, or null if no such command exists.
     *
     * @param alias The alias of the command to find.
     * @return The command with the specified alias, or null if not found.
     */
    public Command getCommand(String alias) {
        for (Command command : commands) {
            if (command.getName().equals(alias))
                return command;
        }
        return null;
    }

    /**
     * Dispatches the provided command.
     *
     * @param message The input message.
     */
    public void dispatch(String message) throws CommandSyntaxException {
        DISPATCHER.execute(message, minecraftClient.getNetworkHandler().getCommandSource());
    }

    @EventHandler
    public void onClientCommand(ChatPreEvent event) {
        if (!event.getMessage().startsWith(prefix)) return;
        event.setCancel(true);
        try {
            dispatch(event.getMessage().substring(1));
        } catch (CommandSyntaxException e) {
            Helper.printChatMessage("§c" + e.getMessage());
        }

        minecraftClient.inGameHud.getChatHud().addToMessageHistory(event.getMessage());
    }

    /**
     * This class represents a command the root command to execute all sub commands.
     * It extends the {@link Command} class and overrides the {@link #build()} method to define the command structure.
     *
     * @author SpigotRCE
     * @since 2.28
     */
    private static class ParadiseCommand extends Command {
        public ParadiseCommand(MinecraftClient minecraftClient) {
            super("paradise", "The paradise command!", minecraftClient);
        }

        @Override
        public LiteralArgumentBuilder<CommandSource> build() {
            LiteralArgumentBuilder<CommandSource> node = literal(getName());
            node.executes(context -> {
                Helper.printChatMessage("Version information " + Constants.VERSION);
                for (Command command : ParadiseClient_Fabric.getCommandManager().getCommands())
                    Helper.printChatMessage(command.getName() + " " +command.getDescription());
                return SINGLE_SUCCESS;
            });

            ParadiseClient_Fabric.getCommandManager().getCommands().forEach(c -> {
                if (c != this) node.then(c.build());
            });

            return node;
        }
    }
}
