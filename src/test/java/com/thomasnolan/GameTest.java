package com.thomasnolan;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void start() {
    }

    @Test
    void move() {
        assertEquals(Game.move(0, 1), Game.squares.get(1));
       //assertEquals(Game.move(0, 6), Game.squares.get(0));
        //assertEquals(Game.move(6, 1), Game.squares.get(1));
        //assertEquals(Game.move(6, 6), Game.squares.get(0));

    }
}