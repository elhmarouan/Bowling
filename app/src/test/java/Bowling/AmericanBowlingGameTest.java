package Bowling;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class AmericanBowlingGameTest {
    BowlingGame bowlingGame;

    private final List<TestCase> testCases = List.of(
            TestCase.builder().entry("9- 9- 9- 9- 9- 9- 9- 9- 9- 9-".split(" ")).score(90).build(),
            TestCase.builder().entry("X X X X X X X X X X X X".split(" ")).score(300).build(),
            TestCase.builder().entry("5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5".split(" ")).score(150).build()
    );

    @DisplayName("Should return correct scores for different test cases")
    @Test
    public void shouldReturnCorrectScoresForTestCases() {
        // GIVEN
        testCases.forEach(testCase -> {
            bowlingGame = new AmericanBowlingGame();

            // WHEN
            bowlingGame.start(testCase.getEntry());

            // THEN
            assertThat(bowlingGame.getTotalGameScore()).isEqualTo(testCase.getScore());
        });
    }
}
