package com.gmail.nextsisui.sisuizero;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class NationManager {
    private static List<Nation> nationList = new ArrayList<>();
    private static List<NationPlayer> nationPlayerList = new ArrayList<>();

    public static List<Nation> getNations(){
        return nationList;
    }

    public static List<NationPlayer> getNationPlayers(){
        return nationPlayerList;
    }

    /**
     * Add NationPlayer to the list.
     * @param player is who added to NationPlayer.
     * @return true : success, false : already added.
     * */
    public static boolean addNationPlayer(Player player){
        if(getNationPlayer(player)!=null) return false;
        nationPlayerList.add(new NationPlayer(player));
        return true;
    }

    /**
     * get NationPlayer from Player.
     * @param player is player who is get NationPlayer
     * @return NationPlayer : success, null : failure
     */
    public static NationPlayer getNationPlayer(Player player){
        for(NationPlayer nationPlayer : nationPlayerList){
            if(nationPlayer.getPlayer().equals(player))
                return nationPlayer;
        }
        return null;
    }

    /**
     * Create a new nation.
     * @param name is what is nation name
     * @param tag is what is nation tag
     * @param president is player who is president of nation
     * @return true : success, false : failure
     */
    public static boolean createNation(String name, String tag, NationPlayer president){
        if(name.length() > 16 || tag.length() > 4) return false;
        if(president == null) return false;
        Nation nation = new Nation(name, tag, president);
        nationList.add(nation);
        return true;
    }


}
