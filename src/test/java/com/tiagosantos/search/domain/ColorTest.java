package com.tiagosantos.search.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ColorTest {

    @Test
    void testResetColor() {
        assertEquals("\033[0m", Color.RESET.getValue());
    }

    @Test
    void testBlackColor() {
        assertEquals("\033[30m", Color.BLACK.getValue());
    }

    @Test
    void testRedColor() {
        assertEquals("\033[31m", Color.RED.getValue());
    }

    @Test
    void testGreenColor() {
        assertEquals("\033[32m", Color.GREEN.getValue());
    }

    @Test
    void testYellowColor() {
        assertEquals("\033[33m", Color.YELLOW.getValue());
    }

    @Test
    void testBlueColor() {
        assertEquals("\033[34m", Color.BLUE.getValue());
    }

    @Test
    void testMagentaColor() {
        assertEquals("\033[35m", Color.MAGENTA.getValue());
    }

    @Test
    void testCyanColor() {
        assertEquals("\033[36m", Color.CYAN.getValue());
    }

    @Test
    void testWhiteColor() {
        assertEquals("\033[37m", Color.WHITE.getValue());
    }
}
