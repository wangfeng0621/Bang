package main.java;

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
            System.out.println(Name+"("+"Player"+ID+"):"+"玩家太啰嗦，已屏蔽他的发言！");
            return false;
        }
    }
}
