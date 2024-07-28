package com.thomasnolan;

import org.junit.jupiter.api.BeforeAll; 
import org.junit.jupiter.api.BeforeEach; 
import org.junit.jupiter.api.AfterEach; 
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.thomasnolan.GameIOHandler;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Locale;
import java.util.Scanner;

import static java.lang.System.in;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    private final InputStream standardIn = System.in;

    private final PrintStream standardOut = System.out;    
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setIn(standardIn);
        System.setOut(standardOut);
    }    

    @Test
    void rollDiceOnceValid() {

        String data = "4\ny\n"; 
        GameIOHandler io = new GameIOHandler(
            new ByteArrayInputStream(data.getBytes()),
            new ByteArrayOutputStream()
        );

        // InputStream sysInBackup = in; // backup System.in to restore it later
        // ByteArrayInputStream in = new ByteArrayInputStream("y\n".getBytes());
        // System.setIn(in);

        Player player = new Player();

        int num = player.rollDice(io);
        assertTrue(1 <= num && num <= 6 );

        //System.setIn(sysInBackup);


    }

    @Test
    void testRollDiceOnceInvalid() {

        // InputStream sysInBackup = in; // backup System.in to restore it later
        // ByteArrayInputStream in = new ByteArrayInputStream("y\n".getBytes());
        // System.setIn(in);

        String data = "4\ny\n"; 
        GameIOHandler io = new GameIOHandler(
            new ByteArrayInputStream(data.getBytes()),
            new ByteArrayOutputStream()
        );


        Player player = new Player();

        int num = player.rollDice(io);

        assertFalse( num < 1);

        // System.setIn(sysInBackup);


    }

    @Test
    void testRollDiceTenValid() {

        InputStream sysInBackup = in; // backup System.in to restore it later
        // ByteArrayInputStream in = new ByteArrayInputStream("y\n".repeat(10).getBytes());
        // System.setIn(in);

        String data = "4\ny\n"; 
        GameIOHandler io = new GameIOHandler(
            new ByteArrayInputStream(data.getBytes()),
            new ByteArrayOutputStream()
        );

        Player player = new Player();

        for (int i = 0; i < 10; i++) {
            int num = player.rollDice(io);
            assertTrue(1 <= num && num <= 6 );
        }

        System.setIn(sysInBackup);

    }
}