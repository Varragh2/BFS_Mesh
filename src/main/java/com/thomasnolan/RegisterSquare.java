package com.thomasnolan;

import java.util.regex.Pattern;

public class RegisterSquare extends Square {

    //https://stackoverflow.com/questions/8204680/java-regex-email
    Pattern regex = Pattern.compile("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b");

    /**
     * The RegisterSquare class has a method run that executes the basic parameters of RegisterSquare
     * the run method checks if a player has registered.
     * Then it asks the player for a first and last name as well as an email.
     * The email is validated with regex, if an invalid email is entered the user is asked again.
     * The player gains 100 mesh bucks from registering.
     * @param player player
     *
     */
    @Override
    public void run(Player player) {
        System.out.println("Welcome to Register User.");
        if (player.hasRegistered) {
            System.out.println("You have already registered.");
            return;
        }
        System.out.println("What is your first name?");
        player.firstName = Game.readInput();

        System.out.println("What is your last name?");
        player.lastName = Game.readInput();

        String email;
        do {
            System.out.println("What is your email address?");
            email = Game.readInput();
            if (!regex.asMatchPredicate().test(email)) {
                System.out.println("Please enter a valid email address.");
            }
        } while (!regex.asMatchPredicate().test(email));

        player.mesh_bucks += 100;
        player.hasRegistered = true;
        player.email = email;

        System.out.println("Welcome " + player.firstName + " " + player.lastName + ", with email " + email);
        System.out.println("Thanks for registering! You earned 100 mesh bucks");
    }
}
