package com.fengguang;

import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by feng on 2016/7/13.
 */
public class GamerPropertiesTest {

    @Test
    //输入一个属性为：姓名：wang，ID：1，角色：杀手
    public void should_properties_when_input_a_man(){
        GamerProperties player = new GamerProperties("wang",1,"Killer");

        assertThat(player.Name, is("wang"));
        assertThat(player.ID, is(1));
        assertThat(player.role.toString(), is("Killer"));
        assertThat(player.State.toString(), is("Alive"));
    }


    @Test
    //玩家具有发言功能，但是每次发言的长度不应该超过100
    public void should_return_true_when_player_say_hello(){
        GamerProperties player = new GamerProperties("wang",1,"Killer");

        assertThat(player.PlayerSpeak("hello!"), is(true));
    }

    @Test
    //玩家发言超过100的时候
    public void should_false_when_Players_spoke_length_of_more_than_100(){
        GamerProperties player = new GamerProperties("wang",1,"Killer");

        assertThat(player.PlayerSpeak("Players spoke length of more than 100Players spoke length of more than 100Players spoke length of more than 100" +
                "Players spoke length of more than 100Players spoke length of more than 100Players spoke length of more than 100Players spoke length of more than 100" +
                "Players spoke length of more than 100Players spoke length of more than 100Players spoke length of more than 100Players spoke length of more than 100"
        ), is(false));
    }

    @Test
    //所有玩家都具有投票的功能
    public void should_turn_Player_id_when_vote_another_player(){
        GamerProperties player = new GamerProperties("wang",1,"Killer");
        ArrayList<Integer> Alive = new ArrayList<>();
        for(int i = 0;i < 11;i++)
            Alive.add(i);

        assertThat(player.vote(Alive,3), is(3));
    }

    @Test
    //玩家能够弃权
    public void should_turn_false_when_vote_nobody(){
        GamerProperties player = new GamerProperties("wang",1,"Killer");
        ArrayList<Integer> Alive = new ArrayList<>();
        for(int i = 0;i < 11;i++)
            Alive.add(i);

        assertThat(player.vote(Alive,-1), is(-1));
    }

    @Test
    //玩家不能投自己的票,否则返回-2
    public void should_turn_false_when_vote_himself(){
        GamerProperties player = new GamerProperties("wang",1,"Killer");
        ArrayList<Integer> Alive = new ArrayList<>();
        for(int i = 0;i < 11;i++)
            Alive.add(i);

        assertThat(player.vote(Alive,1), is(-2));
    }

    @Test
    //玩家不能投已经死亡了的人的票,否则返回-3
    public void should_turn_false_when_vote_a_player_who_is_death(){
        GamerProperties player = new GamerProperties("wang",1,"Killer");
        ArrayList<Integer> Alive = new ArrayList<>();
        for(int i = 0;i < 11;i++)
            Alive.add(i);

        assertThat(player.vote(Alive,11), is(-3));
    }

    @Test
    //杀手玩家具有杀人的功能,实际上杀手杀人也是投票的一种，可以复用投票的方法
    public void should_turn_player_id_when_Killer_want_kill_another_player(){
        GamerProperties player = new GamerProperties("wang",1,"Killer");
        ArrayList<Integer> Alive = new ArrayList<>();
        for(int i = 0;i < 11;i++)
            Alive.add(i);

        assertThat(player.vote(Alive,2), is(2));
    }


}