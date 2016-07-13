package test;

import main.java.GamerProperties;
import org.junit.Test;

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
}