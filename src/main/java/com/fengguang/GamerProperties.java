package com.fengguang;

import java.util.ArrayList;

/**
 * Created by feng on 2016/7/13.
 */
public class GamerProperties {
    public String name;
    public int id;
    public Role role;
    public GamerState state;

    public GamerProperties(String name, int id, String r) {
        this.name = name;
        this.id = id;
        if (r.equals(Role.CIVILIAN.toString())) {
            role = Role.CIVILIAN;
        } else if (r.equals(Role.KILLER.toString())) {
            role = Role.KILLER;
        } else if (r.equals(Role.POLICE.toString())) {
            role = Role.POLICE;
        }
        state = GamerState.ALIVE;
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
