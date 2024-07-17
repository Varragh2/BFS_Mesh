package main;

import java.util.Scanner;

public class Player {

    int mesh_bucks;
    public boolean hasRegistered = false;
    public String firstName;
    public String lastName;
    public String email;
    Scanner input = new Scanner(System.in);

    public int rollDice() {
        System.out.println("Roll the die!(y/n): ");
        input.nextLine();
        int result = (int) (Math.random() * 10 % 6 + 1);
        System.out.println("You rolled a " + result);

        return result;
    }
}
