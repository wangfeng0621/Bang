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

}