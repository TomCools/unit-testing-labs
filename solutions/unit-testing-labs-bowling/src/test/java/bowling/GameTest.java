package bowling;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class GameTest {
    Game game;

    @Before
    public void init() {
        game = new Game();
    }

    private void rollMany(int amountOfRolls, int rollValue) {
        for (int i = 0; i < amountOfRolls; i++) {
            game.roll(rollValue);
        }
    }

    @Test
    public void whenGutterGame_endScoreIsZero() {

        rollMany(20, 0);

        assertThat(game.getScore(), is(0));
    }

    @Test
    public void whenAllOnes_endScoreIsTwenty() {

        rollMany(20, 1);

        assertThat(game.getScore(), is(20));
    }

    @Test
    public void whenSpareAndThreePinsInNextRoll_andAllOtherAreGutters_endScoreIsSixteen() {

        rollSpare();
        game.roll(3);

        rollMany(17, 0);

        assertThat(game.getScore(), is(16));
    }

    @Test
    public void whenStrikeAndSevenInNextFrame_andAllOthersAreGutters_endScoreIsTwentyFour() {

        rollStrike();

        game.roll(3);
        game.roll(4);

        assertThat(game.getScore(), is(24));
    }

    @Test
    public void whenThePerfectGameIsPlayed_endScoreIs300() {

        rollMany(13,10);

        assertThat(game.getScore(), is(300));
    }

    private void rollStrike() {
        game.roll(10);
    }

    private void rollSpare() {
        game.roll(5);
        game.roll(5);
    }
}
