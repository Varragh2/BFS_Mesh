package com.thomasnolan;

import java.util.ArrayList;

/**
 * Initializes game and manages all game play
 */
public class Game {

    /**
     * List of game squares
     */
    public ArrayList<Square> squares;

    /**
     * Game player
     */
    public static Player player;

    private GameIOHandler io;
    private Square startingSquare;
   

    /**
     * Constructor for game
     * @param io handles all user input and output during game play
     */
    public Game(GameIOHandler io){
        
        this.io = io;

        // Setup starting square
        startingSquare = new Square(io) {
            @Override
            public boolean run(Player player) {
                player.mesh_bucks += 50;
                io.printLine("beginning_square_prompt", player.mesh_bucks);
                return true;
            }
        };

        // Setup board
        squares = new ArrayList<>();
        squares.add(startingSquare);
        squares.add(new RegisterSquare(io));
        squares.add(new ReferFriendSquare(io));
        squares.add(new Square(io));
        squares.add(new EducateYourselfSquare(io));
        squares.add(new AcquireHardwareSquare(io));
        squares.add(new InstallEndUserEquipmentSquare(io));
        squares.add(new ConnectToNetworkSquare(io));

    }


    /**
     * The start of the game, this is where everything happens.
     */
    public void start() {

        // Create new player
        player = new Player();

        // Print greeting
        printMessage("greeting");
        printMessage("init_prompt");

        if (io.readYesNo()) {
            printMessage("init_prompt1");
        }
        else {
            printMessage("leave_prompt");
            return;
        }

        // Print out the board
        printMessage("board_intro");
        for(Square square: squares) {
            printMessage(square.toString());  
        }        
        printMessage("\n");
   
        squares.getFirst().run(player);
        do {
            Square square = move(
                player.position, 
                player.rollDice(io)
            );

            player.position = squares.indexOf(square);
            if (square.run(player)) {
                printMessage("square_num", squares.indexOf(square));
            } else {
                // Check if player wants to leave game
                printMessage("leave_game_prompt");
                if (io.readYesNo()) {       
                    endGame();
                }
            }
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
    public Square move(int pos, int num) {
        if (pos + num > squares.size()) {
            return startingSquare;
        }
        return squares.get( (pos + num) % squares.size() );
    }

    private void printMessage(String key, Object... args) {
        io.printLine(key, args);        
    }

    /**
     * Ends the game and the program with exit code 0
     */
    public void endGame() {
        printMessage("end_game");
        io.closeLogFile();
        System.exit(0);
    }

}
