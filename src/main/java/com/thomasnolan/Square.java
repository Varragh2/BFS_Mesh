package com.thomasnolan;

import java.util.regex.Pattern;

/**
 * Abstract class for all game squares
 */
public class Square {

    /**
     * The game input/output handler.
     */
    public GameIOHandler io;
    
    /**
     * The constructor for the square.
     * @param io the game input/output handler.
     */
    public Square(GameIOHandler io) {
        this.io = io;
    }

    /**
     * Executes the square action
     * @param player the current player
     * @return true if the action is sucessful, false otherwise
     */
    public boolean run(Player player) {
        io.printLine("square_prompt");
        return true;
    }

    /**
     * Validates user entered email address
     * @param email the string email address
     * @return true if valid, false otherwise
     */
    public boolean isValidEmail(String email){
        //https://stackoverflow.com/questions/8204680/java-regex-email
        Pattern regex = Pattern.compile("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b");
        return regex.asMatchPredicate().test(email);
    }

    /**
     * Returns string representation of square
     */
    @Override    
    public String toString(){
        return this.getClass().getSimpleName();
    }

}
