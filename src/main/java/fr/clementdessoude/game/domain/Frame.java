package fr.clementdessoude.game.domain;

import java.util.Optional;
import lombok.Data;

@Data
public class Frame {
    private final int firstRoll;
    private Optional<Integer> secondRoll = Optional.empty();

    public Frame(int firstRoll) {
        this.firstRoll = firstRoll;
    }

    public int getFirstRoll() {
        return firstRoll;
    }

    public int getSecondRoll() {
        return secondRoll.orElse(0);
    }

    public void setSecondRoll(int pins) {
        secondRoll = Optional.of(pins);
    }

    public boolean isComplete() {
        return isStrike() || secondRoll.isPresent();
    }

    public int getScore(int firstNextRoll, int secondNextRoll) {
        return getBasicScore() + getAdditionalScore(firstNextRoll, secondNextRoll);
    }

    private int getBasicScore() {
        return getFirstRoll() + getSecondRoll();
    }

    private int getAdditionalScore(int firstNextRoll, int secondNextRoll) {
        if (isStrike()) {
            return firstNextRoll + secondNextRoll;
        }

        if (isSpare()) {
            return firstNextRoll;
        }

        return 0;
    }

    public boolean isStrike() {
        return firstRoll == 10;
    }

    private boolean isSpare() {
        return !isStrike() && getFirstRoll() + getSecondRoll() == 10;
    }
}
