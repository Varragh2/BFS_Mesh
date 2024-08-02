package com.thomasnolan;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach; 
import org.junit.jupiter.api.Test;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private GameIOHandler io;
    
    private final InputStream standardIn = System.in;
    private final PrintStream standardOut = System.out;    
    

    @BeforeEach
    public void setUp() {

        String data = "4\ny\n"; 
        io = new GameIOHandler(
            new ByteArrayInputStream(data.getBytes()),
            new ByteArrayOutputStream()
        );

    }

    @AfterEach
    public void tearDown() {
        System.setIn(standardIn);
        System.setOut(standardOut);
    }    


    @Test
    void initializeBoard() {

        Game game = new Game(io);

        assertEquals(8, game.squares.size());
    }

    @Test
    void movesOnBoard() {
        
        Game game = new Game(io);

        assertAll(
            "Grouped Assertions of Game Moves",
            () -> assertEquals(game.squares.get(1), game.move(0,1)),
            () -> assertEquals(game.squares.get(6), game.move(0,6)),
            () -> assertEquals(game.squares.get(7), game.move(1,6))
          );

    }
}