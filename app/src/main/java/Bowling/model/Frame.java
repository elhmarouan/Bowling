package Bowling.model;

import Bowling.common.Constants;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class Frame {
    private int frameNumber;

    private List<Roll> rolls;

    private int score;

    public int getTotalPinsKnocked() {
        return this.isSpare() || this.isStrike()
                ? Constants.NUMBER_OF_PINS
                : this.rolls.stream().map(Roll::getPinsKnockedDown).reduce(0, Integer::sum);
    }

    public boolean isLast() {
        return this.getFrameNumber() == Constants.NUMBER_OF_FRAMES;
    }

    public boolean isBonus() {
        return this.getFrameNumber() > Constants.NUMBER_OF_FRAMES;
    }

    public boolean isLastOrBonus() {
        return this.getFrameNumber() >= Constants.NUMBER_OF_FRAMES;
    }

    public boolean isStrike() {
        return rolls.stream().anyMatch(Roll::isStrike);
    }

    public boolean isSpare() {
        return rolls.stream().anyMatch(Roll::isSpare);
    }

    public boolean isStrikeOrSpare() {
        return this.isSpare() || this.isStrike();
    }
}
