package main;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    public static ArrayList<Square> squares;
    static Player player = new Player();
    static Scanner input = new Scanner(System.in);

    static {
        squares = new ArrayList<>(7);
        for (int i = 0; i < 6; i++) {
            squares.add(new RegisterSquare());
        }
    }

    /**
     * The start of the game, this is where everything happens.
     */
    public static void start() {
        System.out.println("Hello, welcome to BFS_MESH_GAME!");
        System.out.println("Do you want to play?(y/n): ");
        if (Game.readInput().toLowerCase().startsWith("y")) {
            System.out.println("Lets play!");
        }
        else {
            System.out.println("See you next time.");
            return;
        }


        Square starting_square = move(0, player.rollDice());
        starting_square.run(new Player());
        System.out.println("Square number " + squares.indexOf(starting_square));
    }
    public static Square move(int pos, int num) {
        return squares.get( (pos + num) % squares.size() );
    }

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
    private static void endGame() {
        System.out.println("The game is over.");
        System.exit(0);

    }
}
