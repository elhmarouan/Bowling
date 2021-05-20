package Bowling;

import Bowling.common.Constants;
import Bowling.model.Frame;
import Bowling.model.Roll;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
public class AmericanBowlingGame implements BowlingGame {
    private final List<Frame> frames = new ArrayList<>();

    @Override
    public void start(String[] framesSequence) {
        log.info("Starting a new Ten-Pin Bowling Game with the following frames {}", Arrays.toString(framesSequence));
        int frameNumber = 1;
        for (String frameString : framesSequence) {
            Frame frame = playFrame(frameString, frameNumber++);
            this.frames.add(frame);
        }
        computeScores();
    }

    @Override
    public int getTotalGameScore() {
        return frames.stream().map(Frame::getScore).reduce(0, Integer::sum);
    }

    @Override
    public void showScoresPerFrame() {
        this.frames.forEach(frame -> {
            log.info("Frame {}, Score: {}", frame.getFrameNumber(), frame.getScore());
        });
    }

    private Frame playFrame(String frameString, int frameNumber) {
        Frame frame = new Frame();
        frame.setFrameNumber(frameNumber);
        List<Roll> rollList = frameString.chars()
                .mapToObj(x -> convertToRoll((char) x)).collect(Collectors.toList());
        frame.setRolls(rollList);
        return frame;
    }

    private void computeScores() {
        this.frames.forEach(frame -> {
            if (frame.isStrike() && !frame.isLastOrBonus()) {
                frame.setScore(frame.getTotalPinsKnocked() + getPinsKnockedOnNextTwoRolls(frame));
            } else if (frame.isSpare() && !frame.isLastOrBonus()) {
                frame.setScore(frame.getTotalPinsKnocked() + getPinsKnockedOnNextRoll(frame));
            } else {
                frame.setScore(frame.getTotalPinsKnocked());
            }
        });
    }

    private int getPinsKnockedOnNextRoll(Frame frame) {
        Optional<Frame> optionalNextFrame = getNextFrame(frame);
        return optionalNextFrame.map(nextFrame -> nextFrame.isStrike()
                ? nextFrame.getTotalPinsKnocked()
                : nextFrame.getRolls().get(0).getPinsKnockedDown()).orElse(0);
    }

    private int getPinsKnockedOnNextTwoRolls(Frame frame) {
        Optional<Frame> optionalNextFrame = getNextFrame(frame);
        return optionalNextFrame.map(nextFrame -> nextFrame.isStrike()
                ? nextFrame.getTotalPinsKnocked() + getPinsKnockedOnNextRoll(nextFrame)
                : nextFrame.getTotalPinsKnocked()).orElse(0);
    }

    private Optional<Frame> getNextFrame(Frame frame) {
        return this.frames.stream()
                .filter(nextFrame -> nextFrame.getFrameNumber() == frame.getFrameNumber() + 1)
                .findFirst();
    }

    private Roll convertToRoll(Character rollChar) {
        switch (rollChar) {
            case 'X' -> {
                return Roll.builder().pinsKnockedDown(Constants.NUMBER_OF_PINS).isStrike(true).build();
            }
            case '-' -> {
                return Roll.builder().pinsKnockedDown(0).build();
            }
            case '/' -> {
                return Roll.builder().pinsKnockedDown(Constants.NUMBER_OF_PINS).isSpare(true).build();
            }
            default -> {
                if (Character.isDigit(rollChar)) {
                    return Roll.builder().pinsKnockedDown(Character.getNumericValue(rollChar)).build();
                } else throw new IllegalArgumentException(Constants.ERROR_INVALID_ROLL_CHARACTER);
            }
        }
    }

}
