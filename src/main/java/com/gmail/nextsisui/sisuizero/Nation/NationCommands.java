package com.gmail.nextsisui.sisuizero.Nation;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.gmail.nextsisui.sisuizero.Nation.NationManager.*;

public class NationCommands implements CommandExecutor{

    //接頭辞です。
    private static final String prefix = "[NATION] ";

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        //コンソールからの送信は弾きます。
        if(!(commandSender instanceof Player)){
            commandSender.sendMessage(prefix + ChatColor.GREEN + "\u3053\u306e\u30b3\u30de\u30f3\u30c9\u306f\u30b2\u30fc\u30e0\u5185\u306e\u307f\u6709\u52b9\u3067\u3059\u3002");
            return true;
        }

        //コマンドの引数が１未満だった場合に弾きます
        if(args.length < 1) return false;

        //モード選択です。第一引数で判別します。
        switch(args[0]){
            case "create":
                //必要引数が足りていない場合はその旨を表示します。
                if(args.length != 3){
                    commandSender.sendMessage(prefix + ChatColor.GREEN + "\u5fc5\u8981\u306a\u30d1\u30e9\u30e1\u30fc\u30bf\u304c\u8db3\u308a\u307e\u305b\u3093\u3002");
                    commandSender.sendMessage(prefix + ChatColor.GREEN + "\u4f7f\u3044\u65b9\u306f /nation create " + ChatColor.YELLOW + "\u56fd\u5bb6\u540d \u56fd\u5bb6\u30bf\u30b0" + ChatColor.GREEN + " \u3067\u3059\u3002");
                    return true;
                }

                if (createNation(commandSender, args[1], args[2], ((Player)commandSender)))
                    commandSender.sendMessage(prefix + ChatColor.GREEN + "\u6b63\u5e38\u306b\u56fd\u5bb6\u304c\u4f5c\u6210\u3055\u308c\u307e\u3057\u305f\u3002");

                else
                    commandSender.sendMessage(prefix + ChatColor.GREEN + "\u56fd\u5bb6\u306e\u4f5c\u6210\u306b\u5931\u6557\u3057\u307e\u3057\u305f\u3002");

                break;

            case "remove":
                //必要パラメータが足りていない場合はその旨を表示します。
                if(args.length != 1){
                    commandSender.sendMessage(prefix + ChatColor.GREEN + "\u5fc5\u8981\u306a\u30d1\u30e9\u30e1\u30fc\u30bf\u304c\u8db3\u308a\u307e\u305b\u3093\u3002");
                    commandSender.sendMessage(prefix + ChatColor.GREEN + "\u4f7f\u3044\u65b9\u306f /nation remove " + ChatColor.GREEN + " \u3067\u3059\u3002");
                    return true;
                }

                if(removeNation(commandSender, (Player)commandSender))
                    commandSender.sendMessage(prefix + ChatColor.GREEN + "\u56fd\u5bb6\u306e\u89e3\u4f53\u306b\u6210\u529f\u3057\u307e\u3057\u305f\u3002");
                else
                    commandSender.sendMessage(prefix + ChatColor.GREEN + "\u56fd\u5bb6\u306e\u89e3\u4f53\u306b\u5931\u6557\u3057\u307e\u3057\u305f\u3002");

                break;

            case "info":
            case "information":
                //必要引数が足りていない場合はその旨を表示します。
                if(args.length != 2){
                    commandSender.sendMessage(prefix + ChatColor.GREEN + "\u5fc5\u8981\u306a\u30d1\u30e9\u30e1\u30fc\u30bf\u304c\u8db3\u308a\u307e\u305b\u3093\u3002");
                    commandSender.sendMessage(prefix + ChatColor.GREEN + "\u4f7f\u3044\u65b9\u306f /nation information " + ChatColor.YELLOW + "\u56fd\u5bb6\u540d " + ChatColor.GREEN + " \u3067\u3059\u3002");
                    return true;
                }

                if(informNation(commandSender, args[1]))
                    commandSender.sendMessage(prefix + ChatColor.GREEN + "\u56fd\u5bb6\u60c5\u5831\u306e\u958b\u793a\u306b\u6210\u529f\u3057\u307e\u3057\u305f\u3002");
                else
                    commandSender.sendMessage(prefix + ChatColor.GREEN + "\u56fd\u5bb6\u60c5\u5831\u306e\u958b\u793a\u306b\u5931\u6557\u3057\u307e\u3057\u305f\u3002");

                break;

            case "invite":
                //必要引数が足りていない場合はその旨を表示します。
                if(args.length != 2){
                    commandSender.sendMessage(prefix + ChatColor.GREEN + "\u5fc5\u8981\u306a\u30d1\u30e9\u30e1\u30fc\u30bf\u304c\u8db3\u308a\u307e\u305b\u3093\u3002");
                    commandSender.sendMessage(prefix + ChatColor.GREEN + "\u4f7f\u3044\u65b9\u306f /nation invite " + ChatColor.YELLOW + "\u30d7\u30ec\u30a4\u30e4\u30fc\u540d " + ChatColor.GREEN + " \u3067\u3059\u3002");
                    return true;
                }

                Player player1 = (Player)commandSender;

                //プレイヤーを取得します。Bukkitのシステムを使用。
                Player player2 = Bukkit.getPlayerExact(args[1]);

                //もしもプレイヤーが見つからないならその旨を表示します。
                if(player2 == null){
                    commandSender.sendMessage(prefix + ChatColor.YELLOW + args[1] + ChatColor.GREEN + " \u3068\u3044\u3046\u30d7\u30ec\u30a4\u30e4\u30fc\u306f\u898b\u3064\u304b\u308a\u307e\u305b\u3093\u3067\u3057\u305f\u3002");
                    return true;
                }

                if (inviteNation(commandSender, player1, player2))
                    commandSender.sendMessage(prefix + ChatColor.GREEN + "\u62db\u5f85\u306b\u6210\u529f\u3057\u307e\u3057\u305f\u3002");
                else
                    commandSender.sendMessage(prefix + ChatColor.GREEN + "\u62db\u5f85\u306b\u5931\u6557\u3057\u307e\u3057\u305f\u3002");
                break;

            case "kick":

                break;


            default:
                return false;
        }

        return true;
    }
}
