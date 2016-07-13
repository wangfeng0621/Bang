package test.java;

import main.java.GamerState;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by feng on 2016/7/13.
 */
public class GamerStateTest {

    @Test
    public void should_all_the_gamer_state(){
        GamerState gamerState = new GamerState();

        assertThat(GamerState.state.Death.toString(), is("Death"));
        assertThat(GamerState.state.Alive.toString(), is("Alive"));

    }

}