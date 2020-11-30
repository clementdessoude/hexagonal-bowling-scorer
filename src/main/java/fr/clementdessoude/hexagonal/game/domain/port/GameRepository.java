package fr.clementdessoude.hexagonal.game.domain.port;

import fr.clementdessoude.hexagonal.game.domain.Game;
import java.util.Optional;
import java.util.UUID;

public interface GameRepository {
    void save(Game game);

    Optional<Game> retrieve(UUID id);
}
