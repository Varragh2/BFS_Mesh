package com.thomasnolan;

public class AcquireHardwareSquare extends Square {

    /**
     * Gives the player the option to buy network hardware for mesh bucks.
     * @param player player
     */
    @Override
    public void run(Player player) {
        Game.printMessage("acquire_hardware_square_prompt");

        if (player.mesh_bucks < 100) {
            Game.printMessage("acquire_hardware_square_fail_mesh_bucks");
            return;
        }

        Game.printMessage("acquire_hardware_square_prompt1");
        if (!Game.readYesNo()) {
           Game.printMessage("acquire_hardware_square_fail_no");
            return;
        }
            player.mesh_bucks -= 100;
            player.hardware++;
            Game.printMessage("acquire_hardware_square_success",player.hardware, player.mesh_bucks);
    }
}
