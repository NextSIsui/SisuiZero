package com.gmail.nextsisui.sisuizero;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class FireThrow implements Listener{
    private SisuiZero plugin;
    public FireThrow(SisuiZero plugin) {
        this.plugin = plugin;
    }
    @SuppressWarnings("deprecation")
    @EventHandler
    public void onPlayerInteractBlock(PlayerInteractEvent event) {

        if(event.getPlayer().getInventory().getItemInMainHand().hasItemMeta() &&  event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName() != null && event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("fire")) {

            new BukkitRunnable() {
                final Player player = event.getPlayer();
                Location loc = player.getEyeLocation();
                double distance = 15;
                int i = 0;
                Location parloc = loc;
                Location moto = player.getEyeLocation();
                @Override
                public void run() {
                    i ++;
                    parloc = loc;
                    parloc.add(0,Math.cos(i),0);
                    player.getWorld().spawnParticle(Particle.FLAME, parloc, 1, 0, 0, 0, 0);
                    loc.add(moto.getDirection().normalize());
                    Entity target = SpellMethods.getNearestEntityFromLocation(parloc , 1 , player);
                    if(target != null) {
                        if (!(target instanceof Item)) {
                            player.playSound(loc, Sound.ENTITY_ARROW_HIT_PLAYER, 1, 0);
                            if (target instanceof Player) {
                                ((Player) target).playSound(loc, Sound.ENTITY_GHAST_HURT, 1, 0);
                            }
                            target.setFireTicks(100);
                            this.cancel();
                        }
                    }

                    if(i >= 15 || parloc.distance(moto) > distance) {
                        this.cancel();
                    }
                }

            }.runTaskTimer(plugin, 0, 1);
        }
    }

    public SisuiZero getPlugin() {
        return plugin;
    }

}
