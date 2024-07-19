package com.thomasnolan;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static java.lang.System.in;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Disabled
    @Test
    void testRollDiceOnceValid() {

        InputStream sysInBackup = in; // backup System.in to restore it later
        ByteArrayInputStream in = new ByteArrayInputStream("y\n".getBytes());
        System.setIn(in);

        Player player = new Player();

        int num = player.rollDice();
        assertTrue(1 <= num && num <= 6 );

        System.setIn(sysInBackup);


    }

    @Disabled
    @Test
    void testRollDiceOnceInvalid() {

        InputStream sysInBackup = in; // backup System.in to restore it later
        ByteArrayInputStream in = new ByteArrayInputStream("y\n".getBytes());
        System.setIn(in);

        Player player = new Player();

        int num = player.rollDice();

        assertFalse( num < 1);

        System.setIn(sysInBackup);


    }

    @Disabled
    @Test
    void testRollDiceTenValid() {

        InputStream sysInBackup = in; // backup System.in to restore it later
        ByteArrayInputStream in = new ByteArrayInputStream("y\n".repeat(10).getBytes());
        System.setIn(in);

        Player player = new Player();

        for (int i = 0; i < 10; i++) {
            int num = player.rollDice();
            assertTrue(1 <= num && num <= 6 );
        }

        System.setIn(sysInBackup);

    }
}