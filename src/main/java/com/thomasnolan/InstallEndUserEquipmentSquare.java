package com.thomasnolan;

public class InstallEndUserEquipmentSquare extends Square {
    @Override
    public void run(Player player) {
        Game.printMessage("install_end_user_equipment_prompt");

        if (player.mesh_bucks < 100) {
            Game.printMessage("install_end_user_equipment_fail");
            return;
        }

        Game.printMessage("install_end_user_equipment_prompt1");
        if (!Game.readYesNo()) {
            return;
        }
        player.mesh_bucks -= 100;
        player.hardware++;
        Game.printMessage("install_end_user_equipment_success", player.hardware, player.mesh_bucks);
    }
}
