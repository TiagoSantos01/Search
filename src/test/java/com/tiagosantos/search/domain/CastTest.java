package com.tiagosantos.search.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CastTest {

    private Cast cast;

    @BeforeEach
    void setUp() {
        cast = new Cast("Jared", "Leto");
    }

    @Test
    void testDefaultConstructor() {
        Cast emptyCast = new Cast();
        assertNull(emptyCast.getFistName());
        assertNull(emptyCast.getLastName());
    }

    @Test
    void testGetters() {
        assertEquals("Jared", cast.getFistName());
        assertEquals("Leto", cast.getLastName());
    }

    @Test
    void testSetters() {
        cast.setFistName("Shannon");
        cast.setLastName("Leto");

        assertEquals("Shannon", cast.getFistName());
        assertEquals("Leto", cast.getLastName());
    }

    @Test
    void testFullName() {
        assertEquals("Jared Leto", cast.getFullName());

        cast.setFistName("Shannon");
        cast.setLastName("Jr.");
        assertEquals("Shannon Jr.", cast.getFullName());
    }

    @Test
    void testToString() {
        String expected = "Cast{fistName='Jared', lastName='Leto'}";
        assertEquals(expected, cast.toString());
    }
}
