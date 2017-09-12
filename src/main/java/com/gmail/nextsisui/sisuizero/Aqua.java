package com.gmail.nextsisui.sisuizero;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class Aqua implements Listener {

    private SisuiZero plugin;

    public Aqua(SisuiZero plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent event){

        if(event.getPlayer().getInventory().getItemInMainHand().hasItemMeta() && event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasDisplayName()){
            if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("aqua")){

                new BukkitRunnable(){
                    final Player player = event.getPlayer();
                    Location location = player.getEyeLocation();
                    int i = 0;
                    double distance = 15;

                    @Override
                    public void run(){
                        i++;

                        location.add(0,Math.cos(i),0);

                        player.getWorld().spawnParticle(Particle.WATER_SPLASH, location, 1, 0, 0, 0, 0);

                        location.subtract(0,Math.cos(i),0);

                        location.add(player.getEyeLocation().getDirection().normalize());

                        Entity target = SpellMethods.getNearestEntityFromLocation(location , 1.5 , player);

                        if(target != null) {
                            if (!(target instanceof Item)) {
                                player.playSound(location, Sound.ENTITY_ARROW_HIT_PLAYER, 1, 0);
                                if (target instanceof Player) {
                                    ((Player) target).playSound(location, Sound.ENTITY_GHAST_HURT, 1, 0);
                                }
                                if (target instanceof Damageable) {
                                    ((Damageable) target).damage(10);
                                    this.cancel();
                                }
                            }
                        }

                        if(i >= 15 || location.distance(player.getEyeLocation()) > distance) {
                            this.cancel();
                        }

                    }

                }.runTaskTimer(plugin, 0, 1);



            }
        }

    }

    public SisuiZero getPlugin() {
        return plugin;
    }

}
