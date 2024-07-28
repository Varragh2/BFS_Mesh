package com.thomasnolan;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ConnectToNetworkSquareTest {

    private final InputStream standardIn = System.in;
    private final PrintStream standardOut = System.out;

    @AfterEach
    public void tearDown() {
        System.setIn(standardIn);
        System.setOut(standardOut);
    }


    /**
     * Tests if ConnectToNetworkSquare.run() works correctly
     */
    @Test
    public void connectToNetworkTest() {
        String data = "4\nyes";
        GameIOHandler io = new GameIOHandler(
                new ByteArrayInputStream(data.getBytes()),
                new ByteArrayOutputStream()
        );

        Player player = new Player();
        player.hardware = 1;

        ConnectToNetworkSquare connectToNetworkSquare = new ConnectToNetworkSquare(io);
        boolean gameEnds = connectToNetworkSquare.run(player);

        assertEquals(0, player.hardware);
        assertFalse(gameEnds);

    }
}
