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

    public static void start() {
        System.out.println("Hello, welcome to BFS_MESH_GAME!");
        System.out.println("Do you want to play?(y/n): ");
        if (input.nextLine().toLowerCase().startsWith("y")) {
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
}
