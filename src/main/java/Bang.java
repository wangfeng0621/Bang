package main.java;

/**
 * Created by feng on 2016/7/13.
 */
public class Bang {
    public GamerProperties Player[];
    public int PlayerNumbers;
    public int Killers;
    public int Polices;
    public int Civilians;

    public Bang(int Number, String[] str) {
        PlayerNumbers = Number;
        Killers = Number / 4;
        Polices = Number / 4;
        Civilians = Number - Killers - Polices;
        Player = new GamerProperties[Number];
        String role[]  = {"Killer","Killer","Killer","Civilian","Civilian","Civilian","Civilian","Civilian","Civilian","Police","Police","Police"};
        for(int i = 0; i < Number; i++){
            Player[i] = new GamerProperties(str[i],i,role[i]) ;
        }
    }
}
