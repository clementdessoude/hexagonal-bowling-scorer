package fr.clementdessoude.game.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class GameTest {

    @Test
    void should_compute_correct_scoreIf_all_failed() {
        Game game = new Game();

        rollsWithZeros(game, 20);

        assertEquals(0, game.getScore());
    }

    @Test
    void should_compute_correct_score_if_some_pins_down_in_one_frame() {
        Game game = new Game();

        game.roll(5);
        rollsWithZeros(game, 19);

        assertEquals(5, game.getScore());
    }

    @Test
    void should_compute_correct_score_if_some_pins_down_at_each_frame() {
        Game game = new Game();

        for (int i = 0; i < 10; i++) {
            game.roll(5);
            game.roll(0);
        }

        assertEquals(50, game.getScore());
    }

    @Test
    void should_compute_correct_score_if_one_spare_but_next_is_failed() {
        Game game = new Game();

        game.roll(5);
        game.roll(5);
        rollsWithZeros(game, 18);

        assertEquals(10, game.getScore());
    }

    @Test
    void should_compute_correct_score_if_one_spare_and_next_is_not_failed() {
        Game game = new Game();

        game.roll(5);
        game.roll(5);
        game.roll(2);
        rollsWithZeros(game, 17);

        assertEquals(14, game.getScore());
    }

    @Test
    void should_compute_correct_score_if_one_strike_but_next_two_are_failed() {
        Game game = new Game();

        game.roll(10);
        game.roll(0);
        game.roll(0);
        rollsWithZeros(game, 16);

        assertEquals(10, game.getScore());
    }

    @Test
    void should_compute_correct_score_if_one_strike_but_next_one_is_failed() {
        Game game = new Game();

        game.roll(10);
        game.roll(0);
        game.roll(2);
        rollsWithZeros(game, 16);

        assertEquals(14, game.getScore());
    }

    @Test
    void should_compute_correct_score_if_one_strike() {
        Game game = new Game();

        game.roll(10);
        game.roll(5);
        game.roll(2);
        rollsWithZeros(game, 16);

        assertEquals(24, game.getScore());
    }

    @Test
    void should_compute_correct_score_if_all_strike() {
        Game game = new Game();

        rollsWithNumberOfPins(game, 12, 10);

        assertEquals(300, game.getScore());
    }

    @Test
    void should_compute_correct_score_if_spare_at_last_frame() {
        Game game = new Game();

        rollsWithNumberOfPins(game, 18, 0);
        rollsWithNumberOfPins(game, 2, 5);
        game.roll(3);

        assertEquals(13, game.getScore());
    }

    private void rollsWithZeros(Game game, int numberOfRolls) {
        rollsWithNumberOfPins(game, numberOfRolls, 0);
    }

    private void rollsWithNumberOfPins(Game game, int numberOfRolls, int numberOfPins) {
        for (int i = 0; i < numberOfRolls; i++) {
            game.roll(numberOfPins);
        }
    }
}
