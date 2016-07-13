package test;

import main.java.Role;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by feng on 2016/7/13.
 */
public class RoleTest {

    @Test
    public void should_get_all_game_role_when_game_start(){
        Role role = new Role();

        assertThat(Role.role.Civilian.toString() , is("Civilian"));
        assertThat(Role.role.Killer.toString(), is("Killer"));
        assertThat(Role.role.Police.toString(), is("Police"));
    }

}