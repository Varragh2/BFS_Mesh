package test;

import main.Player;
import main.RegisterSquare;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class RegisterSquareTest {

    @Test
    void run() {

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
}