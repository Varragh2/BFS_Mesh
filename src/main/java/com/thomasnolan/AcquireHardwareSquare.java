package com.thomasnolan;

public class AcquireHardwareSquare extends Square {

    /**
     * Gives the player the option to buy network hardware for mesh bucks.
     * @param player player
     */
    @Override
    public void run(Player player) {
        System.out.println("Welcome to Acquire Hardware\nThis stop will help you get set up to create a new node in the BFS_Mesh network");

        if (player.mesh_bucks < 100) {
            System.out.println("You don't have enough mesh bucks to buy the network hardware. Please visit a register user square or refer a friend.");
            return;
        }

        System.out.println("Do you want to buy network hardware? This costs 100 mesh bucks. (y/n)");
        if (!Game.readYesNo()) {
            return;
        }
            player.mesh_bucks -= 100;
            player.hardware++;
            System.out.println("You successfully bought 1 network hardware! You now have " + player.hardware + " network hardware. And " + player.mesh_bucks + " mesh bucks.");
    }
}
