package com.gmail.nextsisui.sisuizero.Nation;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NationCommands implements CommandExecutor{

    //接頭辞です。
    private static final String prefix = "[NATION] ";

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        //コンソールからの送信は弾きます。
        if(!(commandSender instanceof Player)){
            commandSender.sendMessage(prefix + ChatColor.GREEN + "このコマンドはゲーム内のみ有効です。");
            return true;
        }

        //コマンドの引数が１未満だった場合に弾きます
        if(args.length < 1) return false;

        //モード選択です。第一引数で判別します。
        switch(args[0]){
            case "create":
                //必要引数が足りていない場合はその旨を表示します。
                if(args.length != 3){
                    commandSender.sendMessage(prefix + ChatColor.GREEN + "必要なパラメータが足りません。");
                    commandSender.sendMessage(prefix + ChatColor.GREEN + "使い方は /nation create " + ChatColor.YELLOW + "国家名 国家タグ" + ChatColor.GREEN + " です。");
                    return true;
                }

                if (createNation(commandSender, args[1], args[2], ((Player)commandSender)))
                    commandSender.sendMessage(prefix + ChatColor.GREEN + "正常に国家が作成されました。");

                else
                    commandSender.sendMessage(prefix + ChatColor.GREEN + "国家の作成に失敗しました。");

                break;

            case "info":
            case "information":
                //必要引数が足りていない場合はその旨を表示します。
                if(args.length != 2){
                    commandSender.sendMessage(prefix + ChatColor.GREEN + "必要なパラメータが足りません。");
                    commandSender.sendMessage(prefix + ChatColor.GREEN + "使い方は /nation information " + ChatColor.YELLOW + "国家名 " + ChatColor.GREEN + " です。");
                    return true;
                }

                if(informNation(commandSender, args[1]))
                    commandSender.sendMessage(prefix + ChatColor.GREEN + "国家情報の開示に成功しました。");
                else
                    commandSender.sendMessage(prefix + ChatColor.GREEN + "国家情報の開示に失敗しました。");

                break;

            case "invite":
                //必要引数が足りていない場合はその旨を表示します。
                if(args.length != 2){
                    commandSender.sendMessage(prefix + ChatColor.GREEN + "必要なパラメータが足りません。");
                    commandSender.sendMessage(prefix + ChatColor.GREEN + "使い方は /nation invite " + ChatColor.YELLOW + "プレイヤー名 " + ChatColor.GREEN + " です。");
                    return true;
                }

                Player player1 = (Player)commandSender;

                //プレイヤーを取得します。Bukkitのシステムを使用。
                Player player2 = Bukkit.getPlayerExact(args[1]);

                //もしもプレイヤーが見つからないならその旨を表示します。
                if(player2 == null){
                    commandSender.sendMessage(prefix + ChatColor.YELLOW + args[1] + ChatColor.GREEN + " というプレイヤーは見つかりませんでした。");
                    return true;
                }

                if (inviteNation(commandSender, player1, player2))
                    commandSender.sendMessage(prefix + ChatColor.GREEN + "招待に成功しました。");
                else
                    commandSender.sendMessage(prefix + ChatColor.GREEN + "招待に失敗しました。");
                break;

            case "kick":
                break;


            default:
                return false;
        }

        return true;
    }

    /**
     * 国家作成メソッドです。
     * @param commandSender コマンド送信者です。
     * @param name 国家名です
     * @param tag 国家タグです
     * @param president 国家長です。
     * @return true の場合は国家作成に成功。false の場合は国家作成に失敗。
     */
    private boolean createNation(CommandSender commandSender, String name, String tag, Player president){

        //以下のそれぞれについて、すべての国家に対しての重複をチェックします。
        for (Nation nation : NationManager.getNationList()){
            //国家名の重複をチェックします。していたらその旨を表示して falseを返します。
            if (nation.getName().equalsIgnoreCase(name)){
                commandSender.sendMessage(prefix + ChatColor.YELLOW + name + ChatColor.GREEN + " という国家名は既に使用されています。");
                return false;
            }

            //国家タグの重複をチェックします。していたらその旨を表示して falseを返します。
            if (nation.getTag().equalsIgnoreCase(tag)){
                commandSender.sendMessage(prefix + ChatColor.YELLOW + tag + ChatColor.GREEN + " という国家タグは既に使用されています。");
                return false;
            }

            //国家長がほかの国家に所属していないかどうかをチェックします。
            if (nation.getPresident().getUniqueId().equals(president.getUniqueId())){
                commandSender.sendMessage(prefix + ChatColor.GREEN + "あなたは既に他の国家に所属しています。");
                return false;
            }

        }

        //新しい国家を作成します。
        Nation newNation = new Nation(name, tag, president);

        //NationManagerの国家リストに新しく作った国家を追加します。
        NationManager.getNationList().add(newNation);

        return true;
    }

    /**
     * 国家情報開示のメソッドです。
     * @param commandSender コマンド送信者です。
     * @param name 国家名です。
     * @return true の場合は開示に成功。false の場合は開示に失敗。
     */
    private boolean informNation(CommandSender commandSender, String name){
        //開示する国家です。
        Nation informNation = null;

        //国家リストから国家名を検索します。
        for(Nation nation : NationManager.getNationList())
            if(nation.getName().equalsIgnoreCase(name)) {
                informNation = nation;
                break;
            }

        //もしも国家が見つからなかった場合はその旨を表示し falseを返します。
        if(informNation == null){
            commandSender.sendMessage(prefix + ChatColor.YELLOW + name + ChatColor.GREEN + " という国家は見つかりませんでした。");
            return false;
        }

        //先に国家の情報をまとめておきます。
        String nationName = informNation.getName();
        String nationTag = informNation.getTag();
        String nationPresidentName = informNation.getPresident().getName();
        String nationVicePresidentName = informNation.getVicePresident() == null ? "なし" : informNation.getVicePresident().getName();
        int nationPeopleNumber = informNation.getPeople().size();


        //国家の情報をコマンド送信者に表示します。
        commandSender.sendMessage(prefix + ChatColor.GREEN + "国家名 " + ChatColor.YELLOW + nationName);
        commandSender.sendMessage(prefix + ChatColor.GREEN + "国家タグ " + ChatColor.YELLOW + nationTag);
        commandSender.sendMessage(prefix + ChatColor.GREEN + "国家長 " + ChatColor.YELLOW + nationPresidentName);
        commandSender.sendMessage(prefix + ChatColor.GREEN + "副国家長 " + ChatColor.YELLOW + nationVicePresidentName);
        commandSender.sendMessage(prefix + ChatColor.GREEN + "国民 " + ChatColor.YELLOW + nationPeopleNumber + "人");

        return true;
    }

    /**
     * 国家に招待のメソッドです。
     * @param commandSender コマンド送信者です。
     * @param player1 招待する人です。
     * @param player2 招待される人です。
     * @return true の場合は招待に成功。 false の場合は招待に失敗。
     */
    private boolean inviteNation(CommandSender commandSender, Player player1, Player player2){
        //招待する国家です
        Nation inviteNation = null;

        //国家リストから招待する人の国家を探します。
        for (Nation nation : NationManager.getNationList()){
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
            commandSender.sendMessage(prefix + ChatColor.GREEN + "あなたは国家に所属していないか、必要な権限がありません。");
            return false;
        }

        //招待を相手プレイヤーに送ります。
        player2.sendMessage(prefix + ChatColor.YELLOW + player1.getName() + ChatColor.GREEN + " から " + ChatColor.YELLOW + inviteNation.getName() + " へ招待されました。");

        return true;

    }
}
