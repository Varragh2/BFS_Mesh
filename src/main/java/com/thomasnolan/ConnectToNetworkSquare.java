package com.thomasnolan;

public class ConnectToNetworkSquare extends Square {

    @Override
    public void run(Player player) {
        System.out.println("Welcome to Connect to Network.\nIf you have network hardware or end user equipment you can set it up here.");
        if (player.hardware <= 0) {
            System.out.println("You don't have enough network hardware to connect to the network. Visit an Acquire Hardware Square to gain the hardware required to connect to the network.");
            return;
        }
        System.out.println("You have enough hardware to connect to the network!\nDo you want to connect to the network? (y/n)");
        if (!Game.readYesNo()) {
            return;
        }
        player.hardware--;
        System.out.println("You successfully connected to the network!\nYou have " + player.hardware + " network hardware.");
        System.out.println("You win congratulations!!");
        Game.endGame();
    }
}
