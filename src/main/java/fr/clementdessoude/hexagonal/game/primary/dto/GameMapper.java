package fr.clementdessoude.hexagonal.game.primary.dto;

import fr.clementdessoude.hexagonal.game.domain.Game;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GameMapper {
    GameDto map(Game game);
}
