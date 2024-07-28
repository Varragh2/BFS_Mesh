package com.thomasnolan;

import com.thomasnolan.GameIOHandler;

/**
 * Enables player to purchase network hardware for mesh bucks.
 */
public class AcquireHardwareSquare extends Square {

    /**
    * {@inheritDoc}
    * Constructor for acquire hardware square
     */
    public AcquireHardwareSquare(GameIOHandler io){
        super(io);
    }

    /**
     * Gives the player the option to buy network hardware for mesh bucks.
     * Network hardware costs 100 mesh bucks.
     * @param player player
     */
    @Override
    public boolean run(Player player) {
        super.io.printLine("acquire_hardware_square_prompt");

        if (player.mesh_bucks < 100) {
            super.io.printLine("acquire_hardware_square_fail_mesh_bucks");
            return false;
        }

        super.io.printLine("acquire_hardware_square_prompt1");
        if (!super.io.readYesNo()) {
            super.io.printLine("acquire_hardware_square_fail_no");
            return false;
        }
        
        player.mesh_bucks -= 100;
        player.hardware++;
        super.io.printLine("acquire_hardware_square_success",player.hardware, player.mesh_bucks);
        return true;
    }
}
