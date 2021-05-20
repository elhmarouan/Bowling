package Bowling;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TestCase {
    private final String[] entry;

    private final int score;
}
