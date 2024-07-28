package com.thomasnolan;

/**
 * Allows player to connect to the community network
 */
public class ConnectToNetworkSquare extends Square {

    /**
     * {@inheritDoc}
     * Constructor for connect to network square
     * @param io
     */
    public ConnectToNetworkSquare(GameIOHandler io){
        super(io);
    }

    /**
     * Allows ths player to connect to the network and win the game if they have network hardware available.
     */
    @Override
    public boolean run(Player player) {
        super.io.printLine("connect_network_prompt");
        if (player.hardware <= 0) {
            super.io.printLine("connect_network_fail_hardware");
            return false;
        }
        super.io.printLine("connect_network_success_hardware");
        if (!super.io.readYesNo()) {
            return false;
        }
        player.hardware--;
        super.io.printLine("connect_network_success", player.hardware);
        
        // Time to finish game
        return false;
    }
}
