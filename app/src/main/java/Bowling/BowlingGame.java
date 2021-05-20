package Bowling;

public interface BowlingGame {
    void start(String[] framesSequence);

    int getTotalGameScore();

    void showScoresPerFrame();
}
