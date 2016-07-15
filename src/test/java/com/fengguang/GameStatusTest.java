package com.fengguang;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by feng on 2016/7/14.
 */
public class GameStatusTest {

    @Test
    public void should_all_gameStatu_when_killer_win() {
        GameStatus gameStatus = new GameStatus();

        assertThat(GameStatus.Status.KillerWin.toString(), is("KillerWin"));
        assertThat(GameStatus.Status.KillerLose.toString(), is("KillerLose"));
        assertThat(GameStatus.Status.GameContinue.toString(), is("GameContinue"));
    }

}