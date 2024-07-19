package com.thomasnolan;

import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Game {

    public static ArrayList<Square> squares;
    static Player player = new Player();
    static Scanner input = new Scanner(System.in);
    static Square startingSquare = new Square() {
        @Override
        public void run(Player player) {
            printMessage("beginning_square_prompt", player.mesh_bucks);
            player.mesh_bucks += 50;
        }
    };

    /*
     * Setting up the board
     */
    static {
        squares = new ArrayList<>(15);
        squares.add(startingSquare);
        squares.add(new RegisterSquare());
        squares.add(new Square());
        squares.add(new AcquireHardwareSquare());
        squares.add(new Square());
        squares.add(new InstallEndUserEquipmentSquare());
        squares.add(new ReferFriendSquare());
        squares.add(new Square());
        squares.add(new Square());
        squares.add(new ConnectToNetworkSquare());
        squares.add(new ConnectToNetworkSquare());
        squares.add(new Square());
        squares.add(new ConnectToNetworkSquare());
        squares.add(new AcquireHardwareSquare());

    }

    /**
     * The start of the game, this is where everything happens.
     */
    public static void start() {

        currentLocale = setCurrentLocale(new String[]{"en", "US"});

        printMessage("greeting");
        printMessage("init_prompt");
        if (Game.readYesNo()) {
            printMessage("init_prompt1");
        }
        else {
            printMessage("leave_prompt");
            return;
        }
        squares.get(0).run(player);
        do {
            Square square = move(player.position, player.rollDice());
            player.position = squares.indexOf(square);
            square.run(player);
            printMessage("square_num", squares.indexOf(square));
        } while (true);
    }


    /**
     * Moves the player around the board. The board being the squares ArrayList.
     * Loops around the indices in squares using modulo.
     * If the player loops around the board return the startingSquare, so they can collect mesh bucks.
     * @param pos the starting position of the player
     * @param num the number of square to move forward
     * @return the square at position pos + num looping around the board or staringSquare if loops
     */
    public static Square move(int pos, int num) {
        if (pos + num > squares.size()) {
            return startingSquare;
        }
        return squares.get( (pos + num) % squares.size() );
    }

    /**
     * After asking a yes/no question call this function to read input from the user.
     * @return true means yes, false no
     */
    public static boolean readYesNo() {
        String answer = readInput();
        return answer.toLowerCase().startsWith("y");
    }

    /**
     * All input should be read from this function. It contains global level checks,
     * for example "exit" that quits the game at any time.
     * @return an unformatted string from the user
     */
    public static String readInput() {
        String result = input.nextLine();
        if (result.equalsIgnoreCase("exit") ) {
            Game.endGame();
        }
        return result;
    }

    /**
     * Ends the game and the program with exit code 0
     */
    public static void endGame() {
        printMessage("end_game");
        System.exit(0);
    }

    private static Locale currentLocale;
    //private static ResourceBundle messages;

    /**
     * The start of the game, this is where everything happens.
     */
    public static void start(String[] args) {

        // Set current locale
        currentLocale = setCurrentLocale(args);

        printMessage("greeting");
        printMessage("init_prompt");

        if (Game.readYesNo()) {
            printMessage("init_prompt1");
        }
        else {
            printMessage("leave_prompt");
            return;
        }

        Square starting_square = move(0, player.rollDice());
        starting_square.run(new Player());
        printMessage("square_num", squares.indexOf(starting_square));
    }

    /**
     * Sets the player's locale for the current game.
     * @param  args  command line arguments including language and country
     * @returns Locale returns the current player locale
     * @see {@link https://docs.oracle.com/en/java/javase/22/intl/internationalization-overview.html}
     */
    public static Locale setCurrentLocale(String[] args) {

        // Set init locale
        String language;
        String country;

        if (args.length != 2) {
            language = "en";
            country = "US";
        } else {
            language = args[0];
            country = args[1];
        }

        // builder
        return new Locale.Builder()
                .setLanguage(language)
                .setRegion(country)
                .build();
    }

    /**
     * A convenience method to print to the standard output the message
     * @param key the localization key
     */
    public static void printMessage(String key, Object... args) {
        System.out.printf(getMessage(key) + "%n", args);
    }
    /**
     * Returns the localized resources for the current game.
     * @return ResourceBundle returns resources for the current locale
     */
    public static String getMessage (String key) {
        ResourceBundle bundle = ResourceBundle.getBundle("MessagesBundle", currentLocale);
        return bundle.getString(key);
    }

}
