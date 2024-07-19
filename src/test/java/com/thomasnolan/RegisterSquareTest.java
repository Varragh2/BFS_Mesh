package com.thomasnolan;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class RegisterSquareTest {

    @Disabled
    @Test
    void testRunValid() {

        //https://stackoverflow.com/questions/6415728/junit-testing-with-simulated-user-input
        InputStream sysInBackup = System.in; // backup System.in to restore it later
        ByteArrayInputStream in = new ByteArrayInputStream("Thomas\nNolan\nnolant190@gmail.1111111111com\nnolant190@gmail.com\no".getBytes());
        System.setIn(in);
        // do your thing
        RegisterSquare registerSquare = new RegisterSquare();
        Player player = new Player();
        registerSquare.run(player);

        assertEquals(player.firstName, "Thomas");
        assertEquals(player.lastName, "Nolan");
        assertEquals(player.email, "nolant190@gmail.com");


        // optionally, reset System.in to its original
        System.setIn(sysInBackup);

        registerSquare.run(player);

    }
    @Disabled
    @Test
    void testRunInvalidEmail() {

        //https://stackoverflow.com/questions/6415728/junit-testing-with-simulated-user-input
        InputStream sysInBackup = System.in; // backup System.in to restore it later
        ByteArrayInputStream in = new ByteArrayInputStream("Thomas\nNolan\nnolant190@gmail.1111111111com\nexit".getBytes());
        System.setIn(in);
        // do your thing
        RegisterSquare registerSquare = new RegisterSquare();
        Player player = new Player();
        registerSquare.run(player);

        assertEquals(player.firstName, "Thomas");
        assertEquals(player.lastName, "Nolan");
        assertNull(player.email);


        // optionally, reset System.in to its original
        System.setIn(sysInBackup);

        registerSquare.run(player);

    }


}