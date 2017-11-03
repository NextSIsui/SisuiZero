package com.gmail.nextsisui.sisuizero;

import java.util.ArrayList;
import java.util.List;

public class Nation {

    private final String name;
    private final String tag;
    private final NationPlayer president;
    private List<NationPlayer> vicePresidents = new ArrayList<>();
    private List<NationPlayer> people = new ArrayList<>();

    public Nation(String name, String tag, NationPlayer president){
        this.name = name;
        this.tag = tag;
        this.president = president;
    }

    public String getName() {
        return name;
    }

    public String getTag() {
        return tag;
    }

    public NationPlayer getPresident() {
        return president;
    }

    public List<NationPlayer> getVicePresidents() {
        return vicePresidents;
    }

    public List<NationPlayer> getPeople() {
        return people;
    }

    public void addVicePresident(NationPlayer nationPlayer){
        this.vicePresidents.add(nationPlayer);
    }

    public void addPeople(NationPlayer nationPlayer){
        this.people.add(nationPlayer);
    }

    public void removeVicePresident(NationPlayer nationPlayer){
        this.vicePresidents.remove(nationPlayer);
    }

    public void removePeople(NationPlayer nationPlayer){
        this.vicePresidents.remove(nationPlayer);
    }
}
