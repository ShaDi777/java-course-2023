package edu.hw6;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task6Test {
    @Test
    void testSinglePortFree() {
        assertThat(PortScanner.isTcpPortUsed(1024)).isFalse();
        assertThat(PortScanner.isUdpPortUsed(1024)).isFalse();
    }

    @Test
    void testSinglePortUsed() {
        assertThat(PortScanner.isTcpPortUsed(135)).isTrue();
        assertThat(PortScanner.isUdpPortUsed(138)).isTrue();
    }
}
