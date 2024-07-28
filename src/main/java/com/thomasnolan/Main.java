package com.thomasnolan;

/**
 * Application entry point
 */
public class Main {
    /**
     * Application main method initializes input/output handler and starts game
     * @param args command line arguments
     */
    public static void main(String[] args) {

        GameIOHandler io = new GameIOHandler(System.in, System.out);

        Game game = new Game(io);
        game.start();
    }
}