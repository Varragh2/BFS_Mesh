package com.thomasnolan;

public class Player {

    public int position = 0;
    int mesh_bucks = 0;
    public boolean hasRegistered = false;
    public String firstName;
    public String lastName;
    public String email;

    public int hardware = 0;

    /**
     * Virtually rolls a 6 sided die once.
     * @return number that was rolled
     */
    public int rollDice() {
        Game.printMessage("player_roll_dice");
        Game.readInput();
        int result = (int) (Math.random() * 10 % 6 + 1);
        Game.printMessage("player_roll_dice_success", result);

        return result;
    }

}
