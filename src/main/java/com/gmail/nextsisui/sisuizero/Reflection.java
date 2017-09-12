/*package com.gmail.nextsisui.sisuizero;

import net.minecraft.server.v1_12_R1.Packet;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.util.List;

public class Reflection {

    public static void setValue(Object object, String name, Object value) {

        try {
            Field field = object.getClass().getDeclaredField(name);
            field.setAccessible(true);
            field.set(object, value);
        } catch (Exception ignored) {
        }

    }

    public static Object getValue(Object object, String name) {

        try {
            Field field = object.getClass().getDeclaredField(name);
            field.setAccessible(true);
            return field.get(object);
        } catch (Exception e) {
            return null;
        }

    }

    public static void sendPacket(Packet packet, Player player) {
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }

    public static void sendPacket(Packet packet) {
        for (Player player : getAllOnlinePlayers()) {
            sendPacket(packet, player);
        }
    }

    public static List<Player> getAllOnlinePlayers() {
        @SuppressWarnings("unchecked")
        List<Player> players = (List<Player>) Bukkit.getOnlinePlayers();
        return players;
    }

}
*/
