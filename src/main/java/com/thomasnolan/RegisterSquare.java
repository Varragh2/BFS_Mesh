package com.thomasnolan;

/**
 * New players regsiter details
 */
public class RegisterSquare extends Square {

    /**
     * {@inheritDoc}
     */
    public RegisterSquare(GameIOHandler io){
        super(io);
    }

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
    public boolean run(Player player) {
        boolean endGame = false;
        
        super.io.printLine("register_user_prompt");
        if (!super.io.readYesNo()) {
            return false;
        }
        if (player.hasRegistered) {
            super.io.printLine("register_user_fail");
            return false;
        }
        super.io.printLine("register_user_first_name");
        player.firstName = super.io.readInput(endGame);
        if (endGame) {
            return false;
        }

        super.io.printLine("register_user_last_name");
        player.lastName = super.io.readInput(endGame);
        if (endGame) {
            return false;
        }

        String email;

        super.io.printLine("register_user_email");
        email = super.io.readInput(endGame);
        if (endGame) {
            return false;
        }
        // Validate email
        if (!super.isValidEmail(email)) {
            super.io.printLine("register_user_email_fail");
            return false;
        }

        player.mesh_bucks += 100;
        player.hasRegistered = true;
        player.email = email;

        super.io.printLine("register_user_success", player.firstName, player.lastName, player.email);

        return true;
    }
}
