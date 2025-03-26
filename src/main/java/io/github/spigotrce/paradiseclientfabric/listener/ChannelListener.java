package io.github.spigotrce.paradiseclientfabric.listener;

import io.github.spigotrce.eventbus.event.EventHandler;
import io.github.spigotrce.eventbus.event.listener.Listener;
import io.github.spigotrce.paradiseclientfabric.Constants;
import io.github.spigotrce.paradiseclientfabric.Helper;
import io.github.spigotrce.paradiseclientfabric.ParadiseClient_Fabric;
import io.github.spigotrce.paradiseclientfabric.event.channel.PluginMessageEvent;
import net.minecraft.network.PacketByteBuf;

import java.nio.charset.Charset;
import java.util.Objects;

public class ChannelListener implements Listener {
    @SuppressWarnings("unused")
    @EventHandler
    public void onChannelRegister(PluginMessageEvent event) {
        String channelName = event.getChannel();
        PacketByteBuf buf = event.getBuf();
        try {
            if (Objects.equals(channelName, "minecraft:register") || Objects.equals(channelName, "REGISTER")) // 1.13 channel or 1.8 channel
                for (String splitted : buf.toString(Charset.defaultCharset()).split("\000")) {
                    Helper.printChatMessage("&fChannel: &" + (ParadiseClient_Fabric.NETWORK_MOD.getRegisteredChannelsByName().contains(splitted) ? "c " : "d ") + splitted);
                    if (ParadiseClient_Fabric.NETWORK_MOD.getRegisteredChannelsByName().contains(splitted))
                        Helper.showNotification("Exploit found!", splitted);
                }
            else
                if (channelName.equalsIgnoreCase("minecraft:brand")){
                    return;
                }
                Helper.printChatMessage("&fChannel: &d" + channelName + " &fData: &d" + buf.toString(Charset.defaultCharset()));
        } catch (Exception e) {
            Helper.printChatMessage("&4Error handling listener for payload for channel: " + channelName + " " + e.getMessage());
            Constants.LOGGER.error("&4Error handling listener for channel: {} {}", channelName, e);

        }
    }
}
