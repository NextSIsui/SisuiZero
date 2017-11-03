package com.gmail.nextsisui.sisuizero;

import org.bukkit.entity.Player;

public class NationPlayer {


    private final Player player;
    private Nation nation = null;

    public NationPlayer(Player player) {
        this.player = player;
    }

    public NationPlayer(Player player, Nation nation){
        this.player = player;
        this.nation = nation;
    }

    public Player getPlayer() {
        return player;
    }

    public Nation getNation() {
        return nation;
    }

    public void setNation(Nation nation) {
        this.nation = nation;
    }
}
