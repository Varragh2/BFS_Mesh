package test;

import main.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void rollDice() {
        Player player = new Player();
        for (int i = 0; i < 100; i++) {
            assertNotEquals(1, player.rollDice());
            assertNotEquals(7, player.rollDice());
        }
    }
}