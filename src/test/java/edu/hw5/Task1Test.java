package edu.hw5;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task1Test {
    @Test
    void simpleTest() {
        String time = "2022-03-12, 20:20 - 2022-03-12, 23:50";

        String result = Task1.getDuration(time);

        assertThat(result).isEqualTo("PT3H30M");
    }
}
