package com.thomasnolan;

public class EducateYourselfSquare extends Square {


    /**
     * The constructor for the Educate Yourself Square.
     * @param io the game input/output handler.
     */
    public EducateYourselfSquare(GameIOHandler io) {
        super(io);
    }

    @Override
    public boolean run(Player player) {
        io.printLine("educate_yourself_prompt");
        if (!io.readYesNo()) {
            return false;
        }
        io.printLine("educate_yourself_prompt1");
        return true;
    }
}
