package com.thomasnolan;

/**
 * Enables player to choose to intsall end user equipment
 */
public class InstallEndUserEquipmentSquare extends Square {

    /**
     * {@inheritDoc}
     */
    public InstallEndUserEquipmentSquare(GameIOHandler io){
        super(io);
    }

    /**
     * Player can choose to spend 100 mesh bucks to obtain end user equipment
     */
    @Override
    public boolean run(Player player) {
        super.io.printLine("install_end_user_equipment_prompt");

        if (player.mesh_bucks < 100) {
            super.io.printLine("install_end_user_equipment_fail");
            return false;
        }

        super.io.printLine("install_end_user_equipment_prompt1");
        if (!super.io.readYesNo()) {
            return false;
        }
        player.mesh_bucks -= 100;
        player.hardware++;
        super.io.printLine("install_end_user_equipment_success", player.hardware, player.mesh_bucks);

        return true;
    }
}
