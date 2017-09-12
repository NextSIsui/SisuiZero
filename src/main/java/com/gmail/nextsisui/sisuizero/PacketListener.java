/*package com.gmail.nextsisui.sisuizero;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import net.minecraft.server.v1_12_R1.EnumHand;
import net.minecraft.server.v1_12_R1.PacketPlayInArmAnimation;
import net.minecraft.server.v1_12_R1.PacketPlayOutAnimation;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;


public class PacketListener extends ChannelInboundHandlerAdapter implements Listener {
    private SisuiZero plugin;
    private Player player;

    public PacketListener(Player player){
        this.player = player;
    }

    public PacketListener(SisuiZero plugin){

        this.plugin = plugin;

    }

    public void channelRead(ChannelHandlerContext ctx, Object object) throws Exception {

        if(object instanceof PacketPlayInArmAnimation) {
            PacketPlayInArmAnimation packet = (PacketPlayInArmAnimation) object;
            Reflection.setValue(packet, "d", EnumHand.OFF_HAND);
            super.channelRead(ctx, packet);
        }
        else{
            super.channelRead(ctx, object);
        }

    }

    private void registerPlayer(Player player){

        Channel channel = ((CraftPlayer) player).getHandle().playerConnection.networkManager.channel;

        channel.pipeline().addBefore("packet_handler", "packet_listener", new PacketListener(player));

    }

    @EventHandler
    public void PlayerJoinEvent(PlayerJoinEvent event){
        registerPlayer(event.getPlayer());
    }

    @EventHandler
    public void PlayerToggleSneakEvent(PlayerToggleSneakEvent event){

        PacketPlayOutAnimation packet = new PacketPlayOutAnimation(((CraftPlayer) event.getPlayer()).getHandle(), 1);
        Reflection.setValue(packet, "a", ((CraftPlayer) event.getPlayer()).getHandle().getId());
        Reflection.setValue(packet, "b", 0);
        Reflection.sendPacket(packet);

    }

    public SisuiZero getPlugin(){return plugin;}

}
*/
