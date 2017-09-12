package com.gmail.nextsisui.sisuizero;

import io.netty.channel.ChannelInboundHandlerAdapter;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class SisuiZero extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        super.onEnable();
        getCommand("duel").setExecutor(new DuelMine(this));
        this.getServer().getPluginManager().registerEvents(new FireThrow (this), this);
        this.getServer().getPluginManager().registerEvents(new Aqua (this), this);
        this.getServer().getPluginManager().registerEvents(new DuelMine (this), this);
        //this.getServer().getPluginManager().registerEvents(new PacketListener(this), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

    }
}

