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

    public GamerProperties(String name, int id, String role) {
        this.name = name;
        this.id = id;

        if (role.equals(Role.CIVILIAN.toString())) {
            this.role = Role.CIVILIAN;
        } else if (role.equals(Role.KILLER.toString())) {
            this.role = Role.KILLER;
        } else if (role.equals(Role.POLICE.toString())) {
            this.role = Role.POLICE;
        }

        state = GamerState.ALIVE;
    }

    public boolean playerSpeak(String sentence) {
        if (sentence.length() <= 100) {
            System.out.println(name + "(" + "player" + id + "):" + sentence);
            return true;
        }

        System.out.println(name + "(" + "player" + id + "):" + "This player too wordy, has been shielded his speech!");
        return false;
    }


    public int vote(ArrayList<Integer> aliveList, int vote) {
        if (vote == -1) {
            return -1;
        }
        if (vote == id) {
            return -2;
        }
        int len = aliveList.size();
        for (int i = 0; i < len; i++) {
            if (vote == aliveList.get(i)) {
                return vote;
            }
        }
        return -3;
    }

}
