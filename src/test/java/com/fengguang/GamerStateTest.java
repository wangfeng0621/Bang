package com.fengguang;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by feng on 2016/7/13.
 */
public class GamerStateTest {

    @Test
    public void should_all_the_gamer_state() {
        GamerState gamerState = new GamerState();

        assertThat(GamerState.State.Death.toString(), is("Death"));
        assertThat(GamerState.State.Alive.toString(), is("Alive"));

    }

}