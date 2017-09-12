package com.gmail.nextsisui.sisuizero;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class SpellMethods {

    public static Entity getNearestEntityFromLocation(Location location, double searchDistance, Player player) {
        double distance = Double.MAX_VALUE;
        Entity closestEntity = null;
        for (Entity target : location.getWorld().getEntities()) {
            if (location.distance(target.getLocation()) <= searchDistance) {
                if (distance > location.distance(target.getLocation())) {
                    if (player != null && target == player) continue;
                    closestEntity = target;
                    distance = location.distance(target.getLocation());
                }
            }
        }
        return closestEntity;
    }
    public static Player getNearestPlayerFromLocation(Location location, double searchDistance) {
        double distance = Double.MAX_VALUE;
        Player closestPlayer = null;
        for (Entity target : location.getWorld().getEntities()) {
            if (location.distance(target.getLocation()) <= searchDistance) {
                if (distance > location.distance(target.getLocation())) {
                    if(!(target instanceof  Player))continue;
                    closestPlayer = (Player)target;
                    distance = location.distance(target.getLocation());
                }
            }
        }
        return closestPlayer;
    }

    public Player getLineOfSightPlayer(Player player) {
        Location location = player.getEyeLocation();
        while (location.getBlock().getType().equals(Material.AIR)) {
            for (Entity entity : location.getChunk().getEntities()) {
                if (entity instanceof Player)
                    if (entity != player)
                        if (location.distance(((Player) entity).getEyeLocation()) <= 1)
                            return (Player) entity;
            }
            if (location.distance(player.getEyeLocation()) >= 20) break;
            location.add(location.getDirection().normalize());
        }
        return null;
    }
}
