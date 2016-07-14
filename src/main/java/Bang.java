package main.java;

import java.util.ArrayList;

/**
 * Created by feng on 2016/7/13.
 */
public class Bang {
    public GamerProperties Player[];
    public int PlayerNumbers;
    public int Killers;
    public int Polices;
    public int Civilians;
    public ArrayList<Integer> Alive;
    public GameStatus.status gameStatus;

    public Bang(int Number, String[] str) {
        PlayerNumbers = Number;
        Killers = Number / 4;
        Polices = Number / 4;
        Civilians = Number - Killers - Polices;
        Player = new GamerProperties[Number];
        Alive = new ArrayList<>();
        String role[]  = {"Killer","Killer","Killer","Civilian","Civilian","Civilian","Civilian","Civilian","Civilian","Police","Police","Police"};
        for(int i = 0; i < Number; i++){
            Player[i] = new GamerProperties(str[i],i,role[i]) ;
            Alive.add(i);
        }
        gameStatus = GameStatus.status.GameContinue;
    }


    public void OutVotee(int[] vote) {
        int votee[] = new int[Player.length];
        for(int i = 0; i < PlayerNumbers;i++){
            int id = Alive.get(i);
            votee[Player[id].vote(Alive,vote[i])]++;
        }

        int outPlayer = max(votee);
        if(outPlayer == -1){
            System.out.println("The highest votes more than two or more players, please vote again!");
            return;
        }

        OutPlayer(outPlayer);

    }

    private void OutPlayer(int outPlayer) {
        Player[outPlayer].State = GamerState.state.Death;
        PlayerNumbers--;
        if(Player[outPlayer].role == Role.role.Killer)
            Killers--;
        else if(Player[outPlayer].role == Role.role.Police)
            Polices--;
        else
            Civilians--;
        Alive.remove((Integer) outPlayer);
    }

    private int max(int[] votee) {
        int max = -1;
        int outPlayerID = -1;
        int count = 1;
        for(int i = 0;i < votee.length;i++)
            if(votee[i] > max){
                max = votee[i];
                outPlayerID = i;
                count = 1;
            }
        else if(votee[i] == max){
                count++;
            }
        return count == 1 ? outPlayerID:-1;
    }

    public void killed(int[] killed) {
        int votee[] = new int[Player.length];
        int index = 0;
        for(int i = 0; i < PlayerNumbers && index < killed.length;i++){
            int id = Alive.get(i);
            if(Player[id].role == Role.role.Killer){
                votee[Player[id].vote(Alive,killed[index])]++;
                index++;
            }

        }

        int outPlayer = max(votee);
        if(outPlayer == -1){
            System.out.println("The highest votes more than two or more players, please vote again!");
            return;
        }

        if(Player[outPlayer].role == Role.role.Killer){
            System.out.println("Killer can't kill one of their own!");
            return;
        }

        OutPlayer(outPlayer);
    }


    public boolean checkGameStatus() {
        if(Killers == 0){
            gameStatus = GameStatus.status.KillerLose;
            return false;
        }

        else if(Polices == 0 || Civilians == 0){
            gameStatus = GameStatus.status.KillerWin;
            return false;
        }
        else
            gameStatus = GameStatus.status.GameContinue;
        return true;
    }
}
