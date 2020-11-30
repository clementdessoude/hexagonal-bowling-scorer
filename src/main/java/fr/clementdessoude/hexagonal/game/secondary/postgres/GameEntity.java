package fr.clementdessoude.hexagonal.game.secondary.postgres;

import fr.clementdessoude.hexagonal.game.domain.Game;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GameEntity {
    @Id
    private UUID id;

    @JoinColumn(name = "game")
    @OneToMany(cascade = CascadeType.ALL)
    List<FrameEntity> frames;

    Game toDomain() {
        Game game = new Game(id);

        frames.forEach(
            frame -> {
                game.roll(frame.getFirstRoll());
                game.roll(frame.getSecondRoll());
            }
        );

        return game;
    }

    static GameEntity from(Game game) {
        List<FrameEntity> frames = game
            .getFrames()
            .getFrames()
            .stream()
            .map(
                frame ->
                    FrameEntity
                        .builder()
                        .firstRoll(frame.getFirstRoll())
                        .secondRoll(frame.getSecondRoll())
                        .build()
            )
            .collect(Collectors.toList());

        return GameEntity.builder().id(game.getId()).frames(frames).build();
    }
}
