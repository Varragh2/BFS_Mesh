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
        Game.printMessage("register_user_prompt");
        if (!Game.readYesNo()) {
            return;
        }
        if (player.hasRegistered) {
            Game.printMessage("register_user_fail");
            return;
        }
        Game.printMessage("register_user_first_name");
        player.firstName = Game.readInput();

        Game.printMessage("register_user_last_name");
        player.lastName = Game.readInput();

        String email;
        do {
            Game.printMessage("register_user_email");
            email = Game.readInput();
            if (!regex.asMatchPredicate().test(email)) {
                Game.printMessage("register_user_email_fail");
            }
        } while (!regex.asMatchPredicate().test(email));

        player.mesh_bucks += 100;
        player.hasRegistered = true;
        player.email = email;

        Game.printMessage("register_user_success", player.firstName, player.lastName, player.email);
    }
}
