package com.fengguang;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by feng on 2016/7/13.
 */
public class RoleTest {

    @Test
    public void should_get_all_game_role_when_game_start() {
        Role role = new Role();

        assertThat(Role.Trole.Civilian.toString(), is("Civilian"));
        assertThat(Role.Trole.Killer.toString(), is("Killer"));
        assertThat(Role.Trole.Police.toString(), is("Police"));
    }

}