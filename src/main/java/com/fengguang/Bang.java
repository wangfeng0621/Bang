package com.fengguang;

import java.util.ArrayList;

/**
 * Created by feng on 2016/7/13.
 */
public class Bang {
    public GamerProperties player[];
    public int playerNumbers;
    public int killers;
    public int polices;
    public int civilians;
    public ArrayList<Integer> alive;
    public GameStatus.Status gameStatus;

    public Bang(int number, String[] str) {
        playerNumbers = number;
        killers = number / 4;
        polices = number / 4;
        civilians = number - killers - polices;
        player = new GamerProperties[number];
        alive = new ArrayList<>();
        String role[] = {"Killer", "Killer", "Killer", "Civilian", "Civilian", "Civilian", "Civilian", "Civilian", "Civilian", "Police", "Police", "Police"};
        for (int i = 0; i < number; i++) {
            player[i] = new GamerProperties(str[i], i, role[i]);
            alive.add(i);
        }
        gameStatus = GameStatus.Status.GameContinue;
    }


    public void outVotee(int[] vote) {
        int votee[] = new int[player.length];
        for (int i = 0; i < playerNumbers; i++) {
            int id = alive.get(i);
            votee[player[id].vote(alive, vote[i])]++;
        }

        int outPlayer = max(votee);
        if (outPlayer == -1) {
            System.out.println("The highest votes more than two or more players, please vote again!");
            return;
        }

        outPlayer(outPlayer);

    }

    private void outPlayer(int outPlayer) {
        player[outPlayer].state = GamerState.State.Death;
        playerNumbers--;
        if (player[outPlayer].trole == Role.Trole.Killer) {
            killers--;
        } else if (player[outPlayer].trole == Role.Trole.Police) {
            polices--;
        } else {
            civilians--;
        }
        alive.remove((Integer) outPlayer);
    }

    private int max(int[] votee) {
        int max = -1;
        int outPlayerID = -1;
        int count = 1;
        for (int i = 0; i < votee.length; i++) {
            if (votee[i] > max) {
                max = votee[i];
                outPlayerID = i;
                count = 1;
            } else if (votee[i] == max) {
                count++;
            }
        }
        return count == 1 ? outPlayerID : -1;
    }

    public void killed(int[] killed) {
        int votee[] = new int[player.length];
        int index = 0;
        for (int i = 0; i < playerNumbers && index < killed.length; i++) {
            int id = alive.get(i);
            if (player[id].trole == Role.Trole.Killer) {
                votee[player[id].vote(alive, killed[index])]++;
                index++;
            }

        }

        int outPlayer = max(votee);
        if (outPlayer == -1) {
            System.out.println("The highest votes more than two or more players, please vote again!");
            return;
        }

        if (player[outPlayer].trole == Role.Trole.Killer) {
            System.out.println("Killer can't kill one of their own!");
            return;
        }

        outPlayer(outPlayer);
    }


    public boolean checkGameStatus() {
        if (killers == 0) {
            gameStatus = GameStatus.Status.KillerLose;
            return false;
        } else if (polices == 0 || civilians == 0) {
            gameStatus = GameStatus.Status.KillerWin;
            return false;
        } else {
            gameStatus = GameStatus.Status.GameContinue;
        }
        return true;
    }
}
