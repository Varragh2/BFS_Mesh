package com.thomasnolan;

import org.junit.jupiter.api.BeforeAll; 
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class ReferFriendSquareTest {

    @BeforeAll
    static void startGame(){
        String[] locale = new String[]{"en","GB"};

        InputStream sysInBackup = System.in; // backup System.in to restore it later
        ByteArrayInputStream in = new ByteArrayInputStream("y\n".getBytes());

        Game.start(locale);
    }

    @Test
    void sendEmail() {

        //https://stackoverflow.com/questions/6415728/junit-testing-with-simulated-user-input
        InputStream sysInBackup = System.in; // backup System.in to restore it later
        ByteArrayInputStream in = new ByteArrayInputStream("no\nThomas\nNolan\nnolant190@gmail.com\no".getBytes());
        System.setIn(in);
        // do your thing
        ReferFriendSquare referFriendSquare = new ReferFriendSquare();
        Player player = new Player();
        referFriendSquare.run(player);
                
        assertEquals(player.mesh_bucks, 100);


        // optionally, reset System.in to its original
        System.setIn(sysInBackup);

    }

}