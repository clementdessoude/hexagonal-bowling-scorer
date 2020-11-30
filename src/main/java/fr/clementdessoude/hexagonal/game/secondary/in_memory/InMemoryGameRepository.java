package fr.clementdessoude.hexagonal.game.secondary.in_memory;

import fr.clementdessoude.hexagonal.game.domain.Game;
import fr.clementdessoude.hexagonal.game.domain.port.GameRepository;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;

//@Repository
public class InMemoryGameRepository implements GameRepository {
    private final ConcurrentHashMap<UUID, Game> games = new ConcurrentHashMap<>();

    @Override
    public void save(Game game) {
        games.put(game.getId(), game);
    }

    @Override
    public Optional<Game> retrieve(UUID id) {
        return Optional.ofNullable(games.get(id));
    }
}
