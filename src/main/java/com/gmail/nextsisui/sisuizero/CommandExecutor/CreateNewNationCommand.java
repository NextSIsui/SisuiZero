package com.gmail.nextsisui.sisuizero.CommandExecutor;

import com.gmail.nextsisui.sisuizero.NationManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CreateNewNationCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String string, String[] args){
        if(!(commandSender instanceof Player)) return false;
        if(args.length != 2) return false;

        Player player = (Player)commandSender;

        String s = NationManager.createNation(args[0], args[1], NationManager.getNationPlayer(player))? "You create a new nation!" : "You fail to create a new nation.";
        player.sendMessage(s);
        return true;
    }
}
