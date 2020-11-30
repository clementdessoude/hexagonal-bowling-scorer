package fr.clementdessoude.hexagonal.game.domain.port;

import fr.clementdessoude.hexagonal.game.domain.Game;
import fr.clementdessoude.hexagonal.game.domain.exception.UnknownGameException;
import java.util.UUID;

public interface GameCenter {
    Game startGame();

    void roll(UUID gameId, int rollPin) throws UnknownGameException;

    int getScore(UUID gameId) throws UnknownGameException;
}
