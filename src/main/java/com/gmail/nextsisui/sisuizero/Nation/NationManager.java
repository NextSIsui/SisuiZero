package com.gmail.nextsisui.sisuizero.Nation;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

class NationManager {
    private static List<Nation> nationList = new ArrayList<>();
    private static final String prefix = "[NATION] ";

    static List<Nation> getNationList() {
        return nationList;
    }

    /**
     * 国家作成メソッドです。
     * @param commandSender コマンド送信者です。
     * @param name 国家名です
     * @param tag 国家タグです
     * @param president 国家長です。
     * @return true の場合は国家作成に成功。false の場合は国家作成に失敗。
     */
    static boolean createNation(CommandSender commandSender, String name, String tag, Player president){

        //以下のそれぞれについて、すべての国家に対しての重複をチェックします。
        for (Nation nation : getNationList()){
            //国家名の重複をチェックします。していたらその旨を表示して falseを返します。
            if (nation.getName().equalsIgnoreCase(name)){
                commandSender.sendMessage(prefix + ChatColor.YELLOW + name + ChatColor.GREEN + " \u3068\u3044\u3046\u56fd\u5bb6\u540d\u306f\u65e2\u306b\u4f7f\u7528\u3055\u308c\u3066\u3044\u307e\u3059\u3002");
                return false;
            }

            //国家タグの重複をチェックします。していたらその旨を表示して falseを返します。
            if (nation.getTag().equalsIgnoreCase(tag)){
                commandSender.sendMessage(prefix + ChatColor.YELLOW + tag + ChatColor.GREEN + " \u3068\u3044\u3046\u56fd\u5bb6\u30bf\u30b0\u306f\u65e2\u306b\u4f7f\u7528\u3055\u308c\u3066\u3044\u307e\u3059\u3002");
                return false;
            }

            //国家長がほかの国家に所属していないかどうかをチェックします。
            if (nation.getPresident().getUniqueId().equals(president.getUniqueId())){
                commandSender.sendMessage(prefix + ChatColor.GREEN + "\u3042\u306a\u305f\u306f\u65e2\u306b\u4ed6\u306e\u56fd\u5bb6\u306b\u6240\u5c5e\u3057\u3066\u3044\u307e\u3059\u3002");
                return false;
            }

        }

        //新しい国家を作成します。
        Nation newNation = new Nation(name, tag, president);

        //NationManagerの国家リストに新しく作った国家を追加します。
        getNationList().add(newNation);

        return true;
    }

    /**
     * 国家解体メソッドです。
     * @param commandSender コマンド送信者です。
     * @param president 国家長です。
     * @return true の場合は国家解体に成功。false の場合は国家作成に失敗。
     */
    static boolean removeNation(CommandSender commandSender, Player president){
        //解体する国家です。
        Nation removeNation = null;

        //国家リストから国家を探します。
        for(Nation nation : getNationList()){
            if(nation.getPresident().getUniqueId().equals(president.getUniqueId())){
                removeNation = nation;
                break;
            }
        }

        //国家が見つからなかった場合はその旨を表示します。
        if(removeNation == null){
            commandSender.sendMessage(prefix + ChatColor.GREEN + "\u3042\u306a\u305f\u306f\u56fd\u5bb6\u306b\u6240\u5c5e\u3057\u3066\u3044\u306a\u3044\u304b\u3001\u5fc5\u8981\u306a\u6a29\u9650\u304c\u3042\u308a\u307e\u305b\u3093\u3002");
            return false;
        }

        commandSender.sendMessage(prefix + ChatColor.GREEN + "/accept");

        //国家リストから国家を排除します。
        Nation finalRemoveNation = removeNation;

        getNationList().remove(finalRemoveNation);
        commandSender.sendMessage(prefix + ChatColor.YELLOW + finalRemoveNation.getName() + ChatColor.GREEN + " \u3092\u89e3\u4f53\u3057\u307e\u3057\u305f\u3002");

        return true;
    }

    /**
     * 国家情報開示のメソッドです。
     * @param commandSender コマンド送信者です。
     * @param name 国家名です。
     * @return true の場合は開示に成功。false の場合は開示に失敗。
     */
    static boolean informNation(CommandSender commandSender, String name){
        //開示する国家です。
        Nation informNation = null;

        //国家リストから国家名を検索します。
        for(Nation nation : getNationList())
            if(nation.getName().equalsIgnoreCase(name)) {
                informNation = nation;
                break;
            }

        //もしも国家が見つからなかった場合はその旨を表示し falseを返します。
        if(informNation == null){
            commandSender.sendMessage(prefix + ChatColor.YELLOW + name + ChatColor.GREEN + " \u3068\u3044\u3046\u56fd\u5bb6\u306f\u898b\u3064\u304b\u308a\u307e\u305b\u3093\u3067\u3057\u305f\u3002");
            return false;
        }

        //先に国家の情報をまとめておきます。
        String nationName = informNation.getName();
        String nationTag = informNation.getTag();
        String nationPresidentName = informNation.getPresident().getName();
        String nationVicePresidentName = informNation.getVicePresident() == null ? "\u306a\u3057" : informNation.getVicePresident().getName();
        int nationPeopleNumber = informNation.getPeople().size();


        //国家の情報をコマンド送信者に表示します。
        commandSender.sendMessage(prefix + ChatColor.GREEN + "\u56fd\u5bb6\u540d " + ChatColor.YELLOW + nationName);
        commandSender.sendMessage(prefix + ChatColor.GREEN + "\u56fd\u5bb6\u30bf\u30b0 " + ChatColor.YELLOW + nationTag);
        commandSender.sendMessage(prefix + ChatColor.GREEN + "\u56fd\u5bb6\u9577 " + ChatColor.YELLOW + nationPresidentName);
        commandSender.sendMessage(prefix + ChatColor.GREEN + "\u526f\u56fd\u5bb6\u9577 " + ChatColor.YELLOW + nationVicePresidentName);
        commandSender.sendMessage(prefix + ChatColor.GREEN + "\u56fd\u6c11 " + ChatColor.YELLOW + nationPeopleNumber + "\u4eba");

        return true;
    }

    /**
     * 国家に招待のメソッドです。
     * @param commandSender コマンド送信者です。
     * @param player1 招待する人です。
     * @param player2 招待される人です。
     * @return true の場合は招待に成功。 false の場合は招待に失敗。
     */
    static boolean inviteNation(CommandSender commandSender, Player player1, Player player2){
        //招待する国家です
        Nation inviteNation = null;

        //国家リストから招待する人の国家を探します。
        for (Nation nation : getNationList()){
            if (nation.getPresident().getUniqueId().equals(player1.getUniqueId())){
                inviteNation = nation;
                break;
            } else if (nation.getVicePresident().getUniqueId().equals(player1.getUniqueId())){
                inviteNation = nation;
                break;
            }
        }

        //もし国家が見つからなかったらその旨を表示し falseを返します。
        if (inviteNation == null) {
            commandSender.sendMessage(prefix + ChatColor.GREEN + "\u3042\u306a\u305f\u306f\u56fd\u5bb6\u306b\u6240\u5c5e\u3057\u3066\u3044\u306a\u3044\u304b\u3001\u5fc5\u8981\u306a\u6a29\u9650\u304c\u3042\u308a\u307e\u305b\u3093\u3002");
            return false;
        }

        //招待を相手プレイヤーに送ります。
        player2.sendMessage(prefix + ChatColor.YELLOW + player1.getName() + ChatColor.GREEN + " \u304b\u3089 " + ChatColor.YELLOW + inviteNation.getName() + " \u3078\u62db\u5f85\u3055\u308c\u307e\u3057\u305f\u3002");

        return true;

    }
}
