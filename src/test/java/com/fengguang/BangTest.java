package com.fengguang;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by feng on 2016/7/13.
 */

public class BangTest {

    @Test
    public void should_show_Player_name_when_Player_is_12() {
        String str = "wang zhang yang li zhou zhao yao he gao mo mu lan liu";
        String st[] = str.split(" ");
        String role[] = {"Killer", "Killer", "Killer", "Civilian", "Civilian", "Civilian", "Civilian", "Civilian", "Civilian", "Police", "Police", "Police"};
        Bang bang = new Bang(12, st);

        for (int i = 0; i < 12; i++) {
            assertThat(bang.player[i].state.toString(), is("Alive"));
            assertThat(bang.player[i].name, is(st[i]));
            assertThat(bang.player[i].id, is(i));
            assertThat(bang.player[i].trole.toString(), is(role[i]));
        }
        assertThat(bang.playerNumbers, is(12));
        assertThat(bang.killers, is(3));
        assertThat(bang.polices, is(3));
        assertThat(bang.civilians, is(6));
    }

    @Test
    //大部分玩家投票给一个人，让他出局
    public void should_state_is_Death_when_most_of_player_vote_for_him() {
        String str = "wang zhang yang li zhou zhao yao he gao mo mu lan liu";
        String st[] = str.split(" ");
        String role[] = {"Killer", "Killer", "Killer", "Civilian", "Civilian", "Civilian", "Civilian", "Civilian", "Civilian", "Police", "Police", "Police"};
        Bang bang = new Bang(12, st);
        int vote[] = {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        bang.outVotee(vote);

        assertThat(bang.player[0].state.toString(), is("Death"));
        assertThat(bang.killers, is(2));
        assertThat(bang.playerNumbers, is(11));
        assertThat(bang.alive.size(), is(11));
    }

    @Test
    //获得最高票数的玩家超过两个或者多个
    public void should_vote_oncemore_when_As_votes_for_two_or_more_players() {
        String str = "wang zhang yang li zhou zhao yao he gao mo mu lan liu";
        String st[] = str.split(" ");
        String role[] = {"Killer", "Killer", "Killer", "Civilian", "Civilian", "Civilian", "Civilian", "Civilian", "Civilian", "Police", "Police", "Police"};
        Bang bang = new Bang(12, st);
        int vote[] = {1, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0};

        bang.outVotee(vote);

        assertThat(bang.player[0].state.toString(), is("Alive"));
        assertThat(bang.killers, is(3));
        assertThat(bang.playerNumbers, is(12));
        assertThat(bang.alive.size(), is(12));
    }

    @Test
    public void should_kill_Player5_when_all_killer_want_kill_player5() {
        String str = "wang zhang yang li zhou zhao yao he gao mo mu lan liu";
        String st[] = str.split(" ");
        String role[] = {"Killer", "Killer", "Killer", "Civilian", "Civilian", "Civilian", "Civilian", "Civilian", "Civilian", "Police", "Police", "Police"};
        Bang bang = new Bang(12, st);
        int killed[] = {5, 5, 5};

        bang.killed(killed);

        assertThat(bang.player[5].state.toString(), is("Death"));
        assertThat(bang.civilians, is(5));
        assertThat(bang.playerNumbers, is(11));
        assertThat(bang.alive.size(), is(11));
    }

    @Test
    public void should_return_false_when_As_votes_for_two_or_more_players() {
        String str = "wang zhang yang li zhou zhao yao he gao mo mu lan liu";
        String st[] = str.split(" ");
        String role[] = {"Killer", "Killer", "Killer", "Civilian", "Civilian", "Civilian", "Civilian", "Civilian", "Civilian", "Police", "Police", "Police"};
        Bang bang = new Bang(12, st);
        int killed[] = {5, 4, 3};

        bang.killed(killed);

        assertThat(bang.player[5].state.toString(), is("Alive"));
        assertThat(bang.civilians, is(6));
        assertThat(bang.playerNumbers, is(12));
        assertThat(bang.alive.size(), is(12));
    }

    @Test
    public void should_return_false_when_the_votee_is_themselves() {
        String str = "wang zhang yang li zhou zhao yao he gao mo mu lan liu";
        String st[] = str.split(" ");
        String role[] = {"Killer", "Killer", "Killer", "Civilian", "Civilian", "Civilian", "Civilian", "Civilian", "Civilian", "Police", "Police", "Police"};
        Bang bang = new Bang(12, st);
        int killed[] = {1, 0, 1};

        bang.killed(killed);

        assertThat(bang.player[5].state.toString(), is("Alive"));
        assertThat(bang.civilians, is(6));
        assertThat(bang.playerNumbers, is(12));
        assertThat(bang.alive.size(), is(12));

    }

    @Test
    public void should_return_true_when_all_the_killers_were_find_out() {
        String str = "wang zhang yang li zhou zhao yao he gao mo mu lan liu";
        String st[] = str.split(" ");
        String role[] = {"Killer", "Killer", "Killer", "Civilian", "Civilian", "Civilian", "Civilian", "Civilian", "Civilian", "Police", "Police", "Police"};
        Bang bang = new Bang(12, st);

        //第一轮游戏，杀掉玩家5，投票投掉玩家0
        int killed1[] = {5, 5, 5};
        bang.killed(killed1);
        int vote1[] = {6, 6, 6, 0, 0, 0, 0, 0, 0, 0, 0};
        bang.outVotee(vote1);

        assertThat(bang.killers, is(2));
        assertThat(bang.player[5].state.toString(), is("Death"));
        assertThat(bang.player[0].state.toString(), is("Death"));
        assertThat(bang.gameStatus.toString(), is("GameContinue"));
        assertThat(bang.checkGameStatus(), is(true));

        //第一轮游戏，杀掉玩家6，投票投掉玩家1
        int killed2[] = {6, 6};
        bang.killed(killed2);
        int vote2[] = {7, 7, 1, 1, 1, 1, 1, 1, 1};
        bang.outVotee(vote2);

        assertThat(bang.killers, is(1));
        assertThat(bang.player[6].state.toString(), is("Death"));
        assertThat(bang.player[1].state.toString(), is("Death"));
        assertThat(bang.player[2].state.toString(), is("Alive"));
        assertThat(bang.checkGameStatus(), is(true));
        assertThat(bang.gameStatus.toString(), is("GameContinue"));


        //第一轮游戏，杀掉玩家7，投票投掉玩家2
        int killed3[] = {7};
        bang.killed(killed3);
        int vote3[] = {9, 2, 2, 2, 2, 2, 2};
        bang.outVotee(vote3);

        assertThat(bang.killers, is(0));
        assertThat(bang.player[7].state.toString(), is("Death"));
        assertThat(bang.player[2].state.toString(), is("Death"));
        assertThat(bang.checkGameStatus(), is(false));
        assertThat(bang.gameStatus.toString(), is("KillerLose"));
    }

}