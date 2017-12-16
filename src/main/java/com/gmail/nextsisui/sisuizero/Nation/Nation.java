package com.gmail.nextsisui.sisuizero.Nation;

import org.bukkit.entity.Player;

import java.util.List;

public class Nation {

    //国家名です。日本語でも構いません。
    private String name;

    //国家のタグです。チャット欄などに表示される省略名のようなものです。４文字まで。
    private String tag;

    //国家長です
    private Player president;

    //副国家長です
    private Player vicePresident;

    //国民です。国家長と副国家長はここには含まれていません。
    private List<Player> people;

    //同盟国家です。
    private List<Nation> allies;

    /**
     * コンストラクタです。国家が作られたときに自動で呼ばれます。必ず国家名、タグ、国家長が必要になります。
     * @param name は国家名
     * @param tag は国家タグ
     * @param president は大統領
     */
    public Nation(String name, String tag, Player president){
        this.name = name;
        this.tag = tag;
        this.president = president;
    }

    String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    String getTag() {
        return tag;
    }

    Player getPresident() {
        return president;
    }

    Player getVicePresident() {
        return vicePresident;
    }

    List<Player> getPeople() {
        return people;
    }

    List<Nation> getAllies() {
        return allies;
    }

}
