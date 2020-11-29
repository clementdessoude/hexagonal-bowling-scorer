package fr.clementdessoude.game.domain;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class Frames {
    private List<Frame> frames = new ArrayList<>();

    public void add(Frame frame) {
        frames.add(frame);
    }

    public Frame getLast() {
        return frames.get(frames.size() - 1);
    }

    public int size() {
        return frames.size();
    }

    public boolean isEmpty() {
        return frames.isEmpty();
    }

    public int getScore(int frameIndex) {
        int firstNextRoll = 0;
        int secondNextRoll = 0;

        if (frameIndex + 1 < size()) {
            Frame nextFrame = frames.get(frameIndex + 1);

            firstNextRoll = nextFrame.getFirstRoll();
            if (nextFrame.isStrike()) {
                if (frameIndex + 2 < size()) {
                    Frame secondNextFrame = frames.get(frameIndex + 2);
                    secondNextRoll = secondNextFrame.getFirstRoll();
                }
            } else {
                secondNextRoll = nextFrame.getSecondRoll();
            }
        }

        return frames.get(frameIndex).getScore(firstNextRoll, secondNextRoll);
    }
}
