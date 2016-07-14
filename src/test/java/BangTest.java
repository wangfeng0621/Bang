package test.java;

import main.java.Bang;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by feng on 2016/7/13.
 */
public class BangTest {

    @Test
    public void should_show_Player_name_when_Player_is_12(){
        String str = "wang zhang yang li zhou zhao yao he gao mo mu lan liu";
        String st[] = str.split(" ");
        String role[]  = {"Killer","Killer","Killer","Civilian","Civilian","Civilian","Civilian","Civilian","Civilian","Police","Police","Police"};
        Bang bang = new Bang(12,st);

        for(int i = 0;i < 12;i++){
            assertThat(bang.Player[i].State.toString(), is("Alive"));
            assertThat(bang.Player[i].Name, is(st[i]));
            assertThat(bang.Player[i].ID, is(i));
            assertThat(bang.Player[i].role.toString(), is(role[i]));
        }
        assertThat(bang.PlayerNumbers, is(12));
        assertThat(bang.Killers, is(3));
        assertThat(bang.Polices, is(3));
        assertThat(bang.Civilians, is(6));
    }

    @Test
    //大部分玩家投票给一个人，让他出局
    public void should_state_is_Death_when_most_of_player_vote_for_him(){
        String str = "wang zhang yang li zhou zhao yao he gao mo mu lan liu";
        String st[] = str.split(" ");
        String role[]  = {"Killer","Killer","Killer","Civilian","Civilian","Civilian","Civilian","Civilian","Civilian","Police","Police","Police"};
        Bang bang = new Bang(12,st);
        int vote[] = {1,0,0,0,0,0,0,0,0,0,0,0};

        bang.OutVotee(vote);

        assertThat(bang.Player[0].State.toString(), is("Death"));
        assertThat(bang.Killers, is(2));
        assertThat(bang.PlayerNumbers, is(11));
        assertThat(bang.Alive.size(), is(11));
    }

    @Test
    //获得最高票数的玩家超过两个或者多个
    public void should_vote_oncemore_when_As_votes_for_two_or_more_players(){
        String str = "wang zhang yang li zhou zhao yao he gao mo mu lan liu";
        String st[] = str.split(" ");
        String role[]  = {"Killer","Killer","Killer","Civilian","Civilian","Civilian","Civilian","Civilian","Civilian","Police","Police","Police"};
        Bang bang = new Bang(12,st);
        int vote[] = {1,0,1,1,1,1,1,0,0,0,0,0};

        bang.OutVotee(vote);

        assertThat(bang.Player[0].State.toString(), is("Alive"));
        assertThat(bang.Killers, is(3));
        assertThat(bang.PlayerNumbers, is(12));
        assertThat(bang.Alive.size(), is(12));
    }

}