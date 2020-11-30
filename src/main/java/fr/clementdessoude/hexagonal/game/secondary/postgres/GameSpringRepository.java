package fr.clementdessoude.hexagonal.game.secondary.postgres;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

interface GameSpringRepository extends JpaRepository<GameEntity, UUID> {}
