package com.thomasnolan;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class RegisterSquareTest {

    private final InputStream standardIn = System.in;
    private final PrintStream standardOut = System.out;    

    @AfterEach
    public void tearDown() {
        System.setIn(standardIn);
        System.setOut(standardOut);
    }    


    @Test
    void validRegistration() {

        String data = "4\nyes\nTest\nUser\ntest@example.com\n";
        GameIOHandler io = new GameIOHandler(
            new ByteArrayInputStream(data.getBytes()),
            new ByteArrayOutputStream()
        );

        // LocalizedInputReader input = new LocalizedInputReader(
        //     new Scanner(new ByteArrayInputStream(data.getBytes())), 
        //     Locale.getDefault()
        // );

        // LocalizedPrintStream output = new LocalizedPrintStream(
        //     new ByteArrayOutputStream(), 
        //     false, 
        //    Locale.getDefault());        

        RegisterSquare registerSquare = new RegisterSquare(io);
        Player player = new Player();

        boolean result = registerSquare.run(player);

        assertEquals("Test", player.firstName);
        assertEquals("User", player.lastName);
        assertEquals("test@example.com", player.email);

    }

}