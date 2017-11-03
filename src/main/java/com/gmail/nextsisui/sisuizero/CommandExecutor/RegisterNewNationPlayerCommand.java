package com.gmail.nextsisui.sisuizero.CommandExecutor;

import com.gmail.nextsisui.sisuizero.NationManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RegisterNewNationPlayerCommand implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(!(commandSender instanceof Player)) return false;
        Player player = (Player) commandSender;
        String str = NationManager.addNationPlayer(player) ? "Successfully registered!" : "You are already registered!";
        player.sendMessage(str);
        return false;
    }
}
