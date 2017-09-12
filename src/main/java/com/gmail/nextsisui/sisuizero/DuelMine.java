package com.gmail.nextsisui.sisuizero;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DuelMine implements Listener ,CommandExecutor {
    private SisuiZero plugin;

    public DuelMine(SisuiZero plugin){
        this.plugin = plugin;
    }

        static private Location irons[] = new Location[30], golds[] = new Location[30], diamonds[] = new Location[30], nexus[] = new Location[30];
        static public List<Location> iron = new ArrayList<>();
        static public List<Location> gold = new ArrayList<>();
        static public List<Location> diamond = new ArrayList<>();
        private int ironC = 0, goldC = 0, diamondC = 0, nexusC = 0;
        static public UUID inRed;
        static public UUID inBlue;
        private int redNexusHP = 30, blueNexusHP = 30;
        private boolean addPos = true;
        private boolean ismine = true;

        private boolean isGameNow = true;

        private int ramdomSound = 0;

        @Override
        public boolean onCommand(CommandSender sender , Command command , String label , String[] args){
            if(command.getName().equalsIgnoreCase("duel")) {
                if (!(sender instanceof Player)) return false;
                Player player = (Player) sender;

                switch(args.length){
                    case 0:
                        break;

                    case 1:
                        if(args[0].equalsIgnoreCase("setBlock")){
                            ItemStack blockSetter = new ItemStack(Material.WOOD_HOE);
                            ItemMeta blockSetterMeta = blockSetter.getItemMeta();
                            blockSetterMeta.setDisplayName("BlockSetter");
                            blockSetter.setItemMeta(blockSetterMeta);

                            player.getInventory().addItem(blockSetter);

                            return true;
                        }
                        else if(args[0].equalsIgnoreCase("setShop")){

                            Entity shopper = player.getWorld().spawn(player.getLocation(),Villager.class);
                            shopper.setCustomName("Shopper");

                            return true;
                        }
                        else if(args[0].equalsIgnoreCase("joinRed")){

                            if(inRed == null){
                                inRed = ((Player) sender).getUniqueId();
                                sender.sendMessage("You now join red");
                                return true;
                            }
                            else return false;
                        }
                        else if(args[0].equalsIgnoreCase("joinBlue")){
                            if(inBlue == null){
                                inBlue = ((Player) sender).getUniqueId();

                                sender.sendMessage("You now join blue");
                                return true;
                            }
                            else return false;
                        }
                        else {
                            sender.sendMessage("Command is not true");
                            return false;
                        }

                    default:
                        sender.sendMessage("Command is not true");
                        break;
                }

            }
            return false;

        }

        @EventHandler
        public void BlockBreakEvent(BlockBreakEvent event) {

            Player player = event.getPlayer();
            ItemStack item = player.getInventory().getItemInMainHand();
            addPos = true;

            if(item != null && item.hasItemMeta()) {
                if (item.getItemMeta().getDisplayName().equals("BlockSetter")) {
                    event.setCancelled(true);
                    player.sendMessage("BlockSetter Check");
                    if (event.getBlock() != null) {
                        if (event.getBlock().getType() != null) {
                            switch (event.getBlock().getType()) {
                                case IRON_ORE:
                                    for (Location loc : iron) {
                                        if ((event.getBlock().getLocation().equals(loc))) {
                                            addPos = false;
                                            break;
                                        }
                                    }
                                    if (addPos) {
                                        player.sendMessage("Set Iron of " + ironC);
                                        iron.add(event.getBlock().getLocation());
                                        ironC++;
                                    }
                                    break;

                                case GOLD_ORE:
                                    for (Location loc : gold) {
                                        if ((event.getBlock().getLocation().equals(loc))) {
                                            addPos = false;
                                            break;
                                        }
                                    }
                                    if (addPos) {
                                        player.sendMessage("Set Gold of " + goldC);
                                        gold.add(event.getBlock().getLocation());
                                        goldC++;
                                    }
                                    break;

                                case DIAMOND_ORE:
                                    for (Location loc : diamond) {
                                        if ((event.getBlock().getLocation().equals(loc))) {
                                            addPos = false;
                                            break;
                                        }
                                    }
                                    if (addPos) {
                                        player.sendMessage("Set Diamond of " + diamondC);
                                        diamond.add(event.getBlock().getLocation());
                                        diamondC++;
                                    }
                                    break;

                                case ENDER_STONE:
                                    for(Location loc : nexus){
                                        if(event.getBlock().getLocation().equals(loc)){
                                            addPos = false;
                                            break;
                                        }
                                    }
                                    if(addPos){
                                        player.sendMessage("Set Nexus of " + nexusC);
                                        nexus[nexusC] = event.getBlock().getLocation();
                                        nexusC++;
                                    }

                                    break;
                            }
                        }
                    }
                }
            }

            if(isGameNow){
                ismine = false;

                switch(event.getBlock().getType()) {
                    case IRON_ORE:
                        for (Location loc : irons) {
                            if ((event.getBlock().getLocation().equals(loc))) {
                                ismine = true;
                                break;
                            }
                        }
                        if (ismine) {
                            event.setCancelled(true);

                            event.getBlock().setType(Material.COBBLESTONE);
                            ItemStack iron = new ItemStack(Material.IRON_INGOT, 1);
                            event.getPlayer().getInventory().addItem(iron);
                            event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_ITEM_PICKUP, 1, 1);
                            new BukkitRunnable() {
                                public void run() {

                                    event.getBlock().setType(Material.IRON_ORE);

                                }

                            }.runTaskLater(plugin, 400);
                        }
                        break;


                    case GOLD_ORE:
                        for (Location loc : golds) {
                            if ((event.getBlock().getLocation().equals(loc))) {
                                ismine = true;
                                break;
                            }
                        }
                        if (ismine) {
                            event.setCancelled(true);

                            event.getBlock().setType(Material.COBBLESTONE);
                            ItemStack gold = new ItemStack(Material.GOLD_INGOT, 1);
                            event.getPlayer().getInventory().addItem(gold);
                            event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_ITEM_PICKUP, 1, 1);
                            new BukkitRunnable() {
                                public void run() {

                                    event.getBlock().setType(Material.GOLD_ORE);

                                }

                            }.runTaskLater(plugin, 400);
                        }

                        break;

                    case DIAMOND_ORE:
                        for (Location loc : diamonds) {
                            if (!(event.getBlock().getLocation().equals(loc))) {
                                ismine = true;
                                break;
                            }
                        }
                        if (ismine) {
                            event.setCancelled(true);

                            event.getBlock().setType(Material.COBBLESTONE);
                            ItemStack diamond = new ItemStack(Material.DIAMOND, 1);
                            event.getPlayer().getInventory().addItem(diamond);
                            event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_ITEM_PICKUP, 1, 1);
                            new BukkitRunnable() {
                                public void run() {

                                    event.getBlock().setType(Material.DIAMOND_ORE);

                                }

                            }.runTaskLater(plugin, 500);
                        }

                        break;

                    case ENDER_STONE:
                        event.setCancelled(true);
                        if(event.getBlock().getLocation().equals(nexus[0])){

                            if (event.getPlayer().getUniqueId().equals(inBlue)) {
                                if(redNexusHP > 0) {
                                    redNexusHP--;

                                    event.getPlayer().sendMessage("Now Red Nexus HP is " + redNexusHP);
                                }
                                else if(redNexusHP == 0){
                                    event.getPlayer().sendMessage("Red nexus is now 0 ! You Victory!");

                                    event.getBlock().setType(Material.BEDROCK);

                                    event.getBlock().getWorld().spawnParticle(Particle.EXPLOSION_LARGE,event.getBlock().getLocation(),1,0,0,0,0);
                                    event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_TNT_PRIMED,1,0);
                                }

                            }
                            else if(event.getPlayer().getUniqueId().equals(inRed)){
                                event.getPlayer().sendTitle("You can't break your own nexus!","",0,10,0);

                            }
                        }

                        else if(event.getBlock().getLocation().equals(nexus[1])){

                            if (event.getPlayer().getUniqueId().equals(inRed)){
                                if(blueNexusHP > 1) {
                                    blueNexusHP--;

                                    event.getPlayer().sendMessage("Now Blue Nexus HP is " + blueNexusHP);
                                }
                                else if(blueNexusHP == 1){

                                    event.getPlayer().sendMessage("Blue nexus is now 0 ! You Victory!");

                                    event.getBlock().setType(Material.BEDROCK);

                                    event.getBlock().getWorld().spawnParticle(Particle.EXPLOSION_LARGE,event.getBlock().getLocation(),1,0,0,0,0);
                                    event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_TNT_PRIMED,1,0);

                                }

                            }
                            else if(event.getPlayer().getUniqueId().equals(inBlue)){
                                event.getPlayer().sendTitle("You can't break your own nexus!","",0,10,0);
                            }
                        }


                        switch (ramdomSound % 5) {
                            case 0:
                                event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ANVIL_PLACE, 1, 0);
                                break;

                            case 1:
                                event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ANVIL_PLACE, 1, 1);
                                break;

                            case 2:
                                event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ANVIL_PLACE, 1, 0.8F);
                                break;

                            case 3:
                                event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ANVIL_PLACE, 1, 0.6F);
                                break;

                            case 4:
                                event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ANVIL_PLACE, 1, 0.7F);
                                break;
                        }
                        ramdomSound++;


                        break;
                }
            }


        }

        @EventHandler
        public void PlayerInteractEntityEvent(PlayerInteractEntityEvent event){
            Entity target = event.getRightClicked();
            if(target.getCustomName() != null && target.getCustomName().equals("Shopper")){

                event.getPlayer().openInventory(InventoryMake.selectMode());

            }

        }

        public static class InventoryMake{
    public static Inventory selectMode(){
        Inventory inventory = Bukkit.createInventory(null,27,"Shop");

        ItemStack weapon = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta weaponMeta = weapon.getItemMeta();
        weaponMeta.setDisplayName("Weapons");
        weapon.setItemMeta(weaponMeta);

        ItemStack armor = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemMeta armorMeta = armor.getItemMeta();
        armorMeta.setDisplayName("Armors");
        armor.setItemMeta(armorMeta);

        ItemStack tool = new ItemStack(Material.FIREBALL);
        ItemMeta toolMeta = tool.getItemMeta();
        toolMeta.setDisplayName("Tools");
        tool.setItemMeta(toolMeta);

        inventory.addItem(weapon);
        inventory.addItem(armor);
        inventory.addItem(tool);

        return inventory;

    }
}

}
