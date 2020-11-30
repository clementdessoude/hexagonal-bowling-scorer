package fr.clementdessoude.hexagonal.game.primary;

import fr.clementdessoude.hexagonal.game.domain.Game;
import fr.clementdessoude.hexagonal.game.domain.exception.UnknownGameException;
import fr.clementdessoude.hexagonal.game.domain.port.GameCenter;
import fr.clementdessoude.hexagonal.game.primary.dto.GameDto;
import fr.clementdessoude.hexagonal.game.primary.dto.GameMapper;
import fr.clementdessoude.hexagonal.game.primary.dto.RollDto;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/games")
@Slf4j
public class GameController {
    private final GameCenter gameCenter;
    private final GameMapper gameMapper;

    @PostMapping
    public GameDto checkProjectIsAtStep() {
        log.info("Create game !");
        return gameMapper.map(gameCenter.startGame());
    }

    @GetMapping(path = "{gameId}/score")
    public Integer checkProjectIsAtStep(@PathVariable UUID gameId) {
        try {
            return gameCenter.getScore(gameId);
        } catch (UnknownGameException e) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "No game found with id " + gameId
            );
        }
    }

    @PostMapping(path = "{gameId}/roll")
    public void checkProjectIsAtStep(@PathVariable UUID gameId, @RequestBody RollDto rollDto) {
        try {
            gameCenter.roll(gameId, rollDto.getPinsDown());
        } catch (UnknownGameException e) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "No game found with id " + gameId
            );
        }
    }
}
