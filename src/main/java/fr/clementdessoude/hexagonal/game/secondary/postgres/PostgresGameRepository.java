package fr.clementdessoude.hexagonal.game.secondary.postgres;

import fr.clementdessoude.hexagonal.game.domain.Game;
import fr.clementdessoude.hexagonal.game.domain.port.GameRepository;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostgresGameRepository implements GameRepository {
    private final GameSpringRepository gameSpringRepository;

    @Override
    public void save(Game game) {
        gameSpringRepository.save(GameEntity.from(game));
    }

    @Override
    public Optional<Game> retrieve(UUID id) {
        return gameSpringRepository.findById(id).map(GameEntity::toDomain);
    }
}
