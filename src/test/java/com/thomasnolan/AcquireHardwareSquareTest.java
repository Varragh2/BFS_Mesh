package com.thomasnolan;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AcquireHardwareSquareTest {

    private final InputStream standardIn = System.in;
    private final PrintStream standardOut = System.out;

    @AfterEach
    public void tearDown() {
        System.setIn(standardIn);
        System.setOut(standardOut);
    }


    /**
     * Tests if AcquireHardwareSquare.run() works correctly
     */
    @Test
    public void acquireHardwareSquareTest() {
        String data = "4\nyes";
        GameIOHandler io = new GameIOHandler(
                new ByteArrayInputStream(data.getBytes()),
                new ByteArrayOutputStream()
        );

        AcquireHardwareSquare acquireHardwareSquare = new AcquireHardwareSquare(io);
        Player player = new Player();
        player.mesh_bucks = 100;
        acquireHardwareSquare.run(player);
        assertEquals(1, player.hardware );

    }
}
