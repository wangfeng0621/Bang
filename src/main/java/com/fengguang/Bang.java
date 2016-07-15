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
    public GameStatus gameStatus;

    public Bang(int number, String[] str) {
        playerNumbers = number;
        killers = number / 4;
        polices = number / 4;
        civilians = number - killers - polices;
        player = new GamerProperties[number];
        alive = new ArrayList<>();
        String role[] = {"KILLER", "KILLER", "KILLER", "CIVILIAN", "CIVILIAN", "CIVILIAN", "CIVILIAN", "CIVILIAN", "CIVILIAN", "POLICE", "POLICE", "POLICE"};
        for (int i = 0; i < number; i++) {
            player[i] = new GamerProperties(str[i], i, role[i]);
            alive.add(i);
        }
        gameStatus = GameStatus.GAME_CONTINUE;
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
        player[outPlayer].state = GamerState.DEATH;
        playerNumbers--;
        if (player[outPlayer].role == Role.KILLER) {
            killers--;
        } else if (player[outPlayer].role == Role.POLICE) {
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
            if (player[id].role == Role.KILLER) {
                votee[player[id].vote(alive, killed[index])]++;
                index++;
            }

        }

        int outPlayer = max(votee);
        if (outPlayer == -1) {
            System.out.println("The highest votes more than two or more players, please vote again!");
            return;
        }

        if (player[outPlayer].role == Role.KILLER) {
            System.out.println("KILLER can't kill one of their own!");
            return;
        }

        outPlayer(outPlayer);
    }


    public boolean checkGameStatus() {
        if (killers == 0) {
            gameStatus = GameStatus.KILLER_LOSE;
            return false;
        } else if (polices == 0 || civilians == 0) {
            gameStatus = GameStatus.KILLER_WIN;
            return false;
        } else {
            gameStatus = GameStatus.GAME_CONTINUE;
            return true;
        }
    }
}
