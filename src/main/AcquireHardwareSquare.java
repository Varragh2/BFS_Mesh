package main;

public class AcquireHardwareSquare extends Square {
    @Override
    public void run(Player player) {
        System.out.println("Welcome to Acquire Hardware\nThis stop will help you get set up to create a new node in the BFS_Mesh network");
        player.hardware++;
    }
}
