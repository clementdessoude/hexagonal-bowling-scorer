package fr.clementdessoude.game.domain;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class Frame {
    private List<Integer> rolls = new ArrayList<>();

    public int getRoll(int rollIndex) {
        if (rollIndex >= rolls.size()) return 0;

        return rolls.get(rollIndex);
    }

    public void addRoll(int pins) {
        rolls.add(pins);
    }

    public boolean isComplete() {
        return isStrike() || rolls.size() == 2;
    }

    public int getScore(int firstNextRoll, int secondNextRoll) {
        return getBasicScore() + getAdditionalScore(firstNextRoll, secondNextRoll);
    }

    private int getBasicScore() {
        return getRollScore(0) + getRollScore(1);
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

    private int getRollScore(int rollIndex) {
        if (rollIndex >= rolls.size()) return 0;

        return rolls.get(rollIndex);
    }

    public boolean isStrike() {
        return rolls.get(0) == 10;
    }

    private boolean isSpare() {
        return rolls.get(0) + rolls.get(1) == 10;
    }
}
