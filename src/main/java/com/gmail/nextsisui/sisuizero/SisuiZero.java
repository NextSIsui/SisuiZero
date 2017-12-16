package com.gmail.nextsisui.sisuizero;

import com.gmail.nextsisui.sisuizero.Nation.NationCommands;
import org.bukkit.plugin.java.JavaPlugin;

public final class SisuiZero extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("nation").setExecutor(new NationCommands());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

    }
}

