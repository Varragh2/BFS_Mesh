package com.thomasnolan;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    public static ArrayList<Square> squares;
    static Player player = new Player();
    static Scanner input = new Scanner(System.in);
    static Square startingSquare = new Square() {
        @Override
        public void run(Player player) {
            System.out.println("Welcome to beginning square.");
            System.out.println("Pass go collect 50 mesh bucks!");
            player.mesh_bucks += 50;
            System.out.println("You have " + player.mesh_bucks + " mesh bucks.");
        }
    };

    static {
        squares = new ArrayList<>(12);
        squares.add(startingSquare);
        squares.add(new RegisterSquare());
        //squares.add(new Square());
        squares.add(new AcquireHardwareSquare());
        squares.add(new InstallEndUserEquipmentSquare());
        //squares.add(new Square());
        //squares.add(new Square());
        squares.add(new ConnectToNetworkSquare());
        squares.add(new ConnectToNetworkSquare());
        //squares.add(new Square());
        squares.add(new ConnectToNetworkSquare());
    }

    /**
     * The start of the game, this is where everything happens.
     */
    public static void start() {
        System.out.println("Hello, welcome to BFS_MESH_GAME!");
        System.out.println("Do you want to play?(y/n): ");
        if (Game.readYesNo()) {
            System.out.println("Lets play!");
        }
        else {
            System.out.println("See you next time.");
            return;
        }
        squares.get(0).run(player);
        do {
            Square square = move(player.position, player.rollDice());
            player.position = squares.indexOf(square);
            square.run(player);
            System.out.println("Square number " + squares.indexOf(square));
        } while (true);
    }


    /**
     * Moves the player around the board. The board being the squares ArrayList.
     * Loops around the indices in squares using modulo.
     * If the player loops around the board return the startingSquare, so they can collect mesh bucks.
     * @param pos the starting position of the player
     * @param num the number of square to move forward
     * @return the square at position pos + num looping around the board or staringSquare if loops
     */
    public static Square move(int pos, int num) {
        if (pos + num > squares.size()) {
            return startingSquare;
        }
        return squares.get( (pos + num) % squares.size() );
    }

    /**
     * After asking a yes/no question call this function to read input from the user.
     * @return true means yes, false no
     */
    public static boolean readYesNo() {
        String answer = readInput();
        return answer.toLowerCase().startsWith("y");
    }

    /**
     * All input should be read from this function. It contains global level checks,
     * for example "exit" that quits the game at any time.
     * @return an unformatted string from the user
     */
    public static String readInput() {
        String result = input.nextLine();
        if (result.equalsIgnoreCase("exit") ) {
            Game.endGame();
        }
        return result;
    }

    /**
     * Ends the game and the program with exit code 0
     */
    public static void endGame() {
        System.out.println("The game is over.");
        System.exit(0);
    }
}
