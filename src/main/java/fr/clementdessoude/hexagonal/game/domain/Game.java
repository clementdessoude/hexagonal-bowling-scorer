package fr.clementdessoude.hexagonal.game.domain;

import java.util.UUID;
import lombok.Getter;

public class Game {
    @Getter
    private final UUID id;

    private final Frames frames = new Frames();

    public Game() {
        this(null);
    }

    public Game(UUID id) {
        this.id = id;
    }

    public void roll(int pins) {
        if (isFirstRoll() || isLastFrameComplete()) {
            Frame newFrame = new Frame(pins);
            frames.add(newFrame);
        } else {
            var frame = frames.getLast();
            frame.setSecondRoll(pins);
        }
    }

    public int getScore() {
        int score = 0;

        for (int frameIndex = 0; frameIndex < Math.min(frames.size(), 10); frameIndex++) {
            score += getFrameScore(frameIndex);
        }

        return score;
    }

    private int getFrameScore(int frameIndex) {
        return frames.getScore(frameIndex);
    }

    private boolean isFirstRoll() {
        return frames.isEmpty();
    }

    private boolean isLastFrameComplete() {
        var frame = frames.getLast();

        return frame.isComplete();
    }
}
