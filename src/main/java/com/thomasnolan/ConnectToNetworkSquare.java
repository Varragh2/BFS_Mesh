package com.thomasnolan;

public class ConnectToNetworkSquare extends Square {

    @Override
    public void run(Player player) {
        Game.printMessage("connect_network_prompt");
        if (player.hardware <= 0) {
            Game.printMessage("connect_network_fail_hardware");
            return;
        }
        Game.printMessage("connect_network_success_hardware");
        if (!Game.readYesNo()) {
            return;
        }
        player.hardware--;
        Game.printMessage("connect_network_success", player.hardware);
        Game.endGame();
    }
}
