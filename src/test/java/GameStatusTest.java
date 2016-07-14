package test.java;

import main.java.GameStatus;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by feng on 2016/7/14.
 */
public class GameStatusTest {

    @Test
    public void should_all_gameStatu_when_killer_win(){
        GameStatus gameStatus = new GameStatus();

        assertThat(GameStatus.status.KillerWin.toString(), is("KillerWin"));
        assertThat(GameStatus.status.KillerLose.toString(), is("KillerLose"));
        assertThat(GameStatus.status.GameContinue.toString(), is("GameContinue"));
    }

}