package com.gmail.nextsisui.sisuizero.CommandExecutor;

import com.gmail.nextsisui.sisuizero.NationManager;
import com.gmail.nextsisui.sisuizero.NationPlayer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlayerInvitionCommand implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(!(commandSender instanceof Player)) return false;
        if(args.length != 1) return false;

        Player player = (Player)commandSender;
        NationPlayer nationPlayer = NationManager.getNationPlayer(player);

        if(nationPlayer == null) return false;
        if(nationPlayer.getNation() == null) return false;
        if(!nationPlayer.getNation().getPresident().equals(nationPlayer)) return false;
        Player player1 = Bukkit.getPlayerExact(args[0]);

        if(player1 == null) return false;
        NationPlayer nationPlayer1 = NationManager.getNationPlayer(player1);

        if(nationPlayer1 == null) return false;
        nationPlayer.getNation().addPeople(nationPlayer1);

        return true;
    }
}
