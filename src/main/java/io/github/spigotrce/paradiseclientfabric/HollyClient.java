package io.github.spigotrce.paradiseclientfabric;

import io.github.spigotrce.eventbus.event.EventManager;
import io.github.spigotrce.paradiseclientfabric.command.CommandManager;
import io.github.spigotrce.paradiseclientfabric.exploit.ExploitManager;
import io.github.spigotrce.paradiseclientfabric.listener.ChannelListener;
import io.github.spigotrce.paradiseclientfabric.listener.PacketListener;
import io.github.spigotrce.paradiseclientfabric.mod.*;
import io.github.spigotrce.paradiseclientfabric.packet.*;
import java.util.logging.Logger;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

/**
 * The main class for the HollyClient Fabric mod.
 * <p>
 * This class implements the {@link ModInitializer} interface and is responsible for initializing
 * the various components and modules of the mod when the mod is loaded.
 * </p>
 *
 * @author Lunx
 * @since 1.0
 */
public class HollyClient implements ModInitializer, ClientModInitializer {
    /**
     * The Minecraft client instance.
     */
    public static final MinecraftClient MINECRAFT_CLIENT = MinecraftClient.getInstance();
    /**
     * The instance of {@link EventManager}, which handles the events being fired and listened.
     */
    public static EventManager EVENT_MANAGER;
    /**
     * The instance of {@link BungeeSpoofMod}, which handles BungeeCord spoofing functionality.
     */
    public static BungeeSpoofMod BUNGEE_SPOOF_MOD;
    /**
     * The instance of {@link MiscMod}, which handles miscellaneous functionalities.
     */
    public static MiscMod MISC_MOD;
    /**
     * The instance of {@link HudMod}, which handles HUD (Heads-Up Display) functionalities.
     */
    public static HudMod HUD_MOD;
    /**
     * The instance of {@link ChatRoomMod}, which handles chat room functionalities.
     */
    public static ChatRoomMod CHAT_ROOM_MOD = new ChatRoomMod();
    /**
     * The instance of {@link ExploitMod}, which handles various exploit-related functionalities.
     */
    public static ExploitMod EXPLOIT_MOD;
    /**
     * The instance of {@link CommandManager}, which manages commands in the mod.
     */
    public static CommandManager COMMAND_MANAGER;
    /**
     * The instance of {@link ExploitManager}, which manages different types of exploits.
     */
    public static ExploitManager EXPLOIT_MANAGER;
    /**
     * The instance of {@link NetworkMod}, which manages network-related functionalities.
     */
    public static NetworkMod NETWORK_MOD;
    /**
     * The instance of {@link NetworkConfiguration}, which stores the protocol version selected in VFP.
     */
    public static NetworkConfiguration NETWORK_CONFIGURATION = new NetworkConfiguration();

    public static void onClientInitialize() {
        licenseCheck();
        Logger.getGlobal().info("");
        registerChannels();
        initializeMods();
        initializeManagers();
        initializeListeners();
    }

    private static void licenseCheck() {
        //try {
          //  Connection connection  = DriverManager.getConnection("77.90.12.27", ConfigManager.getLicense(), "");
            //connection.close();
        //} catch (SQLException e) {
            //MinecraftClient.getInstance().close();
        //}
    }

    public static void registerChannels() {
        PayloadTypeRegistry.playC2S().register(VelocityReportPayloadPacket.ID, VelocityReportPayloadPacket.CODEC);
        PayloadTypeRegistry.playC2S().register(PurpurExploitPayloadPacket.ID, PurpurExploitPayloadPacket.CODEC);
        PayloadTypeRegistry.playC2S().register(AuthMeVelocityPayloadPacket.ID, AuthMeVelocityPayloadPacket.CODEC);
        PayloadTypeRegistry.playC2S().register(ChatSentryPayloadPacket.ID, ChatSentryPayloadPacket.CODEC);
        PayloadTypeRegistry.playC2S().register(ECBPayloadPacket.ID, ECBPayloadPacket.CODEC);
        PayloadTypeRegistry.playC2S().register(SignedVelocityPayloadPacket.ID, SignedVelocityPayloadPacket.CODEC);
        PayloadTypeRegistry.playC2S().register(PubSubAdvancedban.ID, PubSubAdvancedban.CODEC);
        PayloadTypeRegistry.playC2S().register(ProxAlertPayloadPacket.ID, ProxAlertPayloadPacket.CODEC);
        PayloadTypeRegistry.playC2S().register(BrandPayloadPacket.ID, BrandPayloadPacket.CODEC);
        PayloadTypeRegistry.playC2S().register(DespNukePayloadPacket.ID, DespNukePayloadPacket.CODEC);
        PayloadTypeRegistry.playC2S().register(TabBridgePayloadPacket.ID, TabBridgePayloadPacket.CODEC);
        PayloadTypeRegistry.playC2S().register(TabBridgePayloadPacket.ID2, TabBridgePayloadPacket.CODEC);
        PayloadTypeRegistry.playC2S().register(TabBridgePayloadPacket.ID3, TabBridgePayloadPacket.CODEC);
    }


    public static void initializeMods() {
        BUNGEE_SPOOF_MOD = new BungeeSpoofMod();
        MISC_MOD = new MiscMod();
        HUD_MOD = new HudMod();
        CHAT_ROOM_MOD = new ChatRoomMod();
        EXPLOIT_MOD = new ExploitMod();
        NETWORK_MOD = new NetworkMod();
    }

    public static void initializeManagers() {
        EVENT_MANAGER = new EventManager();
        EXPLOIT_MANAGER = new ExploitManager(HollyClient.MINECRAFT_CLIENT);
        HollyClient.EXPLOIT_MANAGER.init();
        COMMAND_MANAGER = new CommandManager(HollyClient.MINECRAFT_CLIENT);
        HollyClient.COMMAND_MANAGER.init();
    }

    public static void initializeListeners() {
        HollyClient.EVENT_MANAGER.registerListener(new PacketListener());
        HollyClient.EVENT_MANAGER.registerListener(HollyClient.COMMAND_MANAGER);
        HollyClient.EVENT_MANAGER.registerListener(new ChannelListener());
    }

    @Override
    public void onInitialize() {
        KeyBinding paradiseCommandOpener = KeyBindingHelper.registerKeyBinding(
                new KeyBinding(
                        "Open lunxrise command",
                        InputUtil.Type.KEYSYM,
                        GLFW.GLFW_KEY_COMMA,
                        Constants.MOD_NAME
                )
        );
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (paradiseCommandOpener.wasPressed())
                MinecraftClient.getInstance().setScreen(new ChatScreen(HollyClient.COMMAND_MANAGER.prefix));
        });
    }

    public static boolean isPrivate(){
        return Constants.EDITION.contains("PRIVATE");
    }

    @Override
    public void onInitializeClient() {
        onClientInitialize();
    }
}
