package main;

public class Player {

    int mesh_bucks;
    public boolean hasRegistered = false;
    public String firstName;
    public String lastName;
    public String email;

    /**
     * Virtually rolls a 6 sided die once.
     * @return number that was rolled
     */
    public int rollDice() {
        System.out.println("Roll the die!(y/n): ");
        Game.readInput();
        int result = (int) (Math.random() * 10 % 6 + 1);
        System.out.println("You rolled a " + result);

        return result;
    }

}
