package com.thomasnolan;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * Handle game input and output
 */
public class GameIOHandler {

    private static final String MESSAGES_BUNDLE = "MessagesBundle";
    private static final String GAME_NAME_LOG = "BFS_MESH: ";
    private static final String PLAYER_NAME_LOG = "Player: ";

    private Scanner scanner;
    private PrintStream printer;

    private String logFileName;
    private FileWriter fw;
    private BufferedWriter bw;

    private ResourceBundle bundle;

    /**
     * Constructor for new game input/output handler
     * @param in the input stream
     * @param out the output stream
     */
    public GameIOHandler(InputStream in, OutputStream out) {
        
        scanner = new Scanner(in);
        printer = new PrintStream(out);

        String lang = "";
        String region = "";

        // Prompt for player locale (can't localize this yet...)
        printer.println("Select your language (enter the choice number): \n 1. English\n 2. American English\n 3. Spanish\n 4. Other");
        if (scanner.hasNext()){
            int choice = scanner.nextInt();
            // Make sure all input is cleared
            scanner.nextLine();

            switch (choice) {
                case 1: 
                    lang = "en";
                    region = "GB";
                    break;
                case 2:
                    lang = "en";
                    region = "US";
                    break;
                case 3:
                    lang = "es";
                    region = "ES";
                    break;
                default:
                    break;
            }
        }
        
        Locale currentLocale;

        // Set locale
        if (!lang.isEmpty() && !region.isEmpty()){
            currentLocale = new Locale.Builder().setLanguage(lang).setRegion(region).build();
        } else {
            currentLocale = Locale.getDefault();
        }

        // Create resource bundle from current locale
        bundle = ResourceBundle.getBundle(MESSAGES_BUNDLE, currentLocale);

        initLogger();
    }

    /**
     * Initialize game play logger
     */
    private void initLogger(){

        String directoryName = "./logs";
        String fileName = "BFS_MESH" + System.currentTimeMillis() + ".txt";

        // Check if directory exists
        File directory = new File(directoryName);
        if (! directory.exists()){
            directory.mkdir();
        }

        //Set log file name
        logFileName = directoryName + "/" + fileName;

        File file = new File(logFileName);
        try{
            // Create file writer
            fw = new FileWriter(file.getAbsoluteFile(), true);
            //bw = new BufferedWriter(fw);
            //bw.write(data);
            //bw.close();
            //fw.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }

        logLine(GAME_NAME_LOG + "Starting new game at " + System.currentTimeMillis()+ "\n");
    }

    /**
     * Prints localized string to the output stream based on key
     * @param key the string to retrieve localized messages
     * @param args the list of values to format the printed string
     */
    public void printLine(String key, Object... args){
        if (bundle.containsKey(key)){
            String msg = bundle.getString(key);
            String formattedMsg = String.format(msg + "%n", args);

            printer.print(formattedMsg);
            //Add to game log
            logLine(GAME_NAME_LOG + formattedMsg);
        } else {
            printer.print(key);
        }
    }

    /**
     * Returns localized string based on key
     * @param key the string to retrieve localized messages
     * @param args the list of values to format the printed string
     * @return the localized string
     */
    public String getString(String key, Object... args){
        if (bundle.containsKey(key)){
            String msg = bundle.getString(key);
            return String.format(msg + "%n", args);
        } else {
            return key;
        }
    }

    /**
     * Reads the user input from the input stream
     * @param endGame the boolean flag to check if user input 'exit' to end game
     * @return the user input string
     */
    public String readInput(boolean endGame){
        if (scanner.hasNextLine()){
            String result = scanner.nextLine();

            //Add to game log
            logLine(PLAYER_NAME_LOG + result + "\n");

            //Check if user wants to end game
            endGame = (result.equalsIgnoreCase(bundle.getString("exit")));
            return result;
        } else {
            return "";
        }

    }

    /**
     * Reads a yes or no user response from the input stream
     * @return true if 'yes', false otherwise
     */
    public boolean readYesNo() {
        boolean endGame = false;
        String answer = this.readInput(endGame);
        return answer.toLowerCase().startsWith(bundle.getString("read_yes_no"));
    }

    /**
     * Adds line to game log
     * @param data the string to append to the game log
     */
    public void logLine(String data){
        
        //File file = new File(logFileName);
        try{
            //fw = new FileWriter(file.getAbsoluteFile(), true);
            bw = new BufferedWriter(fw);
            bw.write(data);
            bw.flush();
            //bw.close();
            //fw.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Closes game log file
     */
    public void closeLogFile(){
        try {
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
