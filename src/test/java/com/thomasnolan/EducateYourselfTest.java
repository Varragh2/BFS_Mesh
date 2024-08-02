package com.thomasnolan;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class EducateYourselfTest {

    private final InputStream standardIn = System.in;
    private final PrintStream standardOut = System.out;

    @AfterEach
    public void tearDown() {
        System.setIn(standardIn);
        System.setOut(standardOut);
    }


    /**
     * Tests if EducateYourself.run() works correctly
     */
    @Test
    public void educateYourselfTest() {
        String data = "4\nyes";
        GameIOHandler io = new GameIOHandler(
                new ByteArrayInputStream(data.getBytes()),
                new ByteArrayOutputStream()
        );

        Player player = new Player();
        int hardware = player.hardware;

        EducateYourselfSquare educateYourselfSquare = new EducateYourselfSquare(io);
        boolean result = educateYourselfSquare.run(player);

        assertEquals(hardware, player.hardware);
        assertTrue(result);

    }
}
