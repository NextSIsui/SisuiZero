package com.gmail.nextsisui.sisuizero;

import com.gmail.nextsisui.sisuizero.CommandExecutor.*;
import org.bukkit.plugin.java.JavaPlugin;

public final class SisuiZero extends JavaPlugin {

    @Override
    public void onEnable() {

        getCommand("CreateNation").setExecutor(new CreateNewNationCommand());
        getCommand("Declaration").setExecutor(new DeclarationOfWarCommand());
        getCommand("InvitePlayer").setExecutor(new PlayerInvitionCommand());
        getCommand("ExpelPlayer").setExecutor(new PlayerExpultionCommand());
        getCommand("RegisterNationPlayer").setExecutor(new RegisterNewNationPlayerCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

    }
}

