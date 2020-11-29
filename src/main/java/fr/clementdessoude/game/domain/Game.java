package fr.clementdessoude.game.domain;

import lombok.Data;

@Data
public class Game {
    private Frames frames = new Frames();

    public void roll(int pins) {
        if (isFirstRoll() || isLastFrameComplete()) {
            Frame newFrame = new Frame();
            frames.add(newFrame);
        }

        var frame = frames.getLast();
        frame.addRoll(pins);
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
