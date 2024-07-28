package com.thomasnolan;

/**
 * Player class manages player settings and die rolls.
 */
public class Player {

    /**
     * The current position of the player on the board.
     */
    public int position = 0;

    /**
     * The number of points, represented as 'Mesh Bucks' the play currently owns.
     */
    public int mesh_bucks = 0;

    /**
     * True if the player has registered already.
     */
    public boolean hasRegistered = false;

    /**
     * The player first name.
     */
    public String firstName;

    /**
     * The player last name.
     */
    public String lastName;

    /**
     * The player email.
     */
    public String email;

    /**
     * The current number of network hardware owned by the player.
     */
    public int hardware = 0;

    /**
     * Constructor for player class
     */
    public Player(){
        position = 0;
        mesh_bucks = 0;
        hasRegistered = false;
        firstName = "";
        lastName = "";
        email = "";
    }

    /**
     * Virtually rolls a 6 sided die once.
     * @return number that was rolled
     */
    public int rollDice(GameIOHandler io) {
        boolean endGame = false;

        io.printLine("player_roll_dice");
        io.readInput(endGame);
        int result = (int) (Math.random() * 10 % 6 + 1);
        io.printLine("player_roll_dice_success", result);

        return 1;
    }

}
