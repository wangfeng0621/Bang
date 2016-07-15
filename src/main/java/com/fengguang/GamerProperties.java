package com.fengguang;

import java.util.ArrayList;

/**
 * Created by feng on 2016/7/13.
 */
public class GamerProperties {
    public String name;
    public int id;
    public Role.Trole trole;
    public GamerState.State state;

    public GamerProperties(String name, int id, String r) {
        this.name = name;
        this.id = id;
        if (r.equals(Role.Trole.Civilian.toString())) {
            trole = Role.Trole.Civilian;
        } else if (r.equals(Role.Trole.Killer.toString())) {
            trole = Role.Trole.Killer;
        } else if (r.equals(Role.Trole.Police.toString())) {
            trole = Role.Trole.Police;
        }
        state = GamerState.State.Alive;
    }

    public boolean playerSpeak(String str) {
        if (str.length() <= 100) {
            System.out.println(name + "(" + "player" + id + "):" + str);
            return true;
        } else {
            System.out.println(name + "(" + "player" + id + "):" + "This player too wordy, has been shielded his speech!");
            return false;
        }
    }


    public int vote(ArrayList<Integer> alive, int votee) {
        if (votee == -1) {
            return -1;
        }
        if (votee == id) {
            return -2;
        }
        int len = alive.size();
        for (int i = 0; i < len; i++) {
            if (votee == alive.get(i)) {
                return votee;
            }
        }
        return -3;
    }

}
