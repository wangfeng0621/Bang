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

}