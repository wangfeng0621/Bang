package com.fengguang;

import java.util.ArrayList;

/**
 * Created by feng on 2016/7/13.
 */
public class GamerProperties {
    public String Name;
    public int ID;
    public Role.role role;
    public GamerState.state State;

    public GamerProperties(String name, int id, String r) {
        Name = name;
        ID = id;
        if(r.equals(Role.role.Civilian.toString()) )
            role = Role.role.Civilian;
        else if(r.equals(Role.role.Killer.toString()))
            role = Role.role.Killer;
        else if(r.equals(Role.role.Police.toString()))
            role = Role.role.Police;
        State = GamerState.state.Alive;
    }

    public boolean PlayerSpeak(String str) {
        if(str.length() <= 100 ){
            System.out.println(Name+"("+"Player"+ID+"):"+str);
            return true;
        }
        else{
            System.out.println(Name+"("+"Player"+ID+"):"+"This player too wordy, has been shielded his speech!");
            return false;
        }
    }


    public int vote(ArrayList<Integer> Alive, int votee) {
        if(votee == -1) return -1;
        if(votee == ID) return -2;
        int len = Alive.size();
        for(int i = 0;i < len;i++)
            if(votee == Alive.get(i))
                return votee;
        return -3;
    }

}
