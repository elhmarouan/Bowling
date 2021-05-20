package Bowling;

import java.util.List;

public interface BowlingGame {
    void start(List<String> framesSequence);

    int getTotalGameScore();

    void showScoresPerFrame();
}
