package fr.clementdessoude.hexagonal.game.domain;

import fr.clementdessoude.hexagonal.game.domain.exception.UnknownGameException;
import fr.clementdessoude.hexagonal.game.domain.port.GameCenter;
import fr.clementdessoude.hexagonal.game.domain.port.GameRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameCenterImpl implements GameCenter {
    private final GameRepository gameRepository;

    public Game startGame() {
        UUID id = UUID.randomUUID();
        Game newGame = new Game(id);

        gameRepository.save(newGame);

        return newGame;
    }

    public void roll(UUID gameId, int rollPin) throws UnknownGameException {
        Game game = gameRepository.retrieve(gameId).orElseThrow(UnknownGameException::new);
        game.roll(rollPin);
        gameRepository.save(game);
    }

    public int getScore(UUID gameId) throws UnknownGameException {
        Game game = gameRepository.retrieve(gameId).orElseThrow(UnknownGameException::new);

        return game.getScore();
    }
}
