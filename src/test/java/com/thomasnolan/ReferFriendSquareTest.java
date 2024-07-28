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

import static org.junit.jupiter.api.Assertions.*;

class ReferFriendSquareTest {

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
    void givenSystemOutRedirection_whenInvokePrintln_thenOutputCaptorSuccess() {
        System.out.print("Hello World!");
            
        assertEquals("Hello World!", outputStreamCaptor.toString()
          .trim());
    }

    /**
     * Helper function to read input
     * @param data
     */
    void provideInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }    

    @Test
    void sendEmailUpdatesMeshBucks() {

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
        //     Locale.getDefault());        


        // Create new square and player
        ReferFriendSquare referFriendSquare = new ReferFriendSquare(io);
        Player player = new Player();

        //provideInput("yes\nTest\nUser\nshea.nolan@gmail.com");

        // Method under test
        referFriendSquare.run(player);
                
        assertEquals(100, player.mesh_bucks);
   
        assertEquals("Sent message successfully....", outputStreamCaptor.toString().trim());

        // assertEquals("Welcome to Refer a Friend.\r\nDo you want to refer a friend? (y/n)\r\nWhat is your friend's first name?\r\nWhat is your friend's last name?\r\nWhat is your friend's email address?\r\nSent message successfully....\r\nWelcome Test User, with email shea.nolan@gmail.com.\r\nThanks for registering your friend! You earned 100 mesh bucks."
        //     , outputStreamCaptor.toString()
        //   .trim());

        //   assertTrue(outputStreamCaptor.toString().contains("Welcome Test User"));
        //   assertTrue(outputStreamCaptor.toString().contains("You earned 100 mesh bucks"));
  
    }

}