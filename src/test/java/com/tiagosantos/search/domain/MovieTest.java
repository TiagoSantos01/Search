package com.tiagosantos.search.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MovieTest {

    private Movie movie;
    private Cast cast1;
    private Cast cast2;

    @BeforeEach
    void setUp() {
        cast1 = new Cast("Jared", "Leto");
        cast2 = new Cast("Shannon", "Leto");

        List<Cast> castList = Arrays.asList(cast1, cast2);

        movie = new Movie("30 Seconds to Mars: Live in Malaysia", 2011, castList, "/path/to/movie/file");
    }

    @Test
    void testGetters() {
        assertEquals("30 Seconds to Mars: Live in Malaysia", movie.getName());
        assertEquals(2011, movie.getYear());
        assertEquals(2, movie.getCasts().size());
        assertEquals("/path/to/movie/file", movie.getPathFile());
    }

    @Test
    void testSetters() {
        movie.setName("New Movie Title");
        movie.setYear(2020);
        movie.setPathFile("/new/path/to/movie/file");

        assertEquals("New Movie Title", movie.getName());
        assertEquals(2020, movie.getYear());
        assertEquals("/new/path/to/movie/file", movie.getPathFile());
    }

    @Test
    void testArtists() {
        List<Cast> artists = movie.getCasts();
        assertEquals(2, artists.size());
        assertEquals("Jared Leto", artists.get(0).getFullName());
        assertEquals("Shannon Leto", artists.get(1).getFullName());
    }

    @Test
    void testToString() {
        String expected = "Movie{name='30 Seconds to Mars: Live in Malaysia', year=2011, " +
                "casts=[Cast{fistName='Jared', lastName='Leto'}, Cast{fistName='Shannon', lastName='Leto'}], " +
                "pathFile='/path/to/movie/file'}";
        assertEquals(expected, movie.toString());
    }
}
