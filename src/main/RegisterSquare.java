package main;

import java.util.regex.Pattern;

public class RegisterSquare extends Square {

    //https://stackoverflow.com/questions/8204680/java-regex-email
    Pattern regex = Pattern.compile("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b");

    @Override
    public void run(Player player) {
        System.out.println("Welcome to Register User.");
        if (player.hasRegistered) {
            System.out.println("You have already registered.");
            return;
        }
        System.out.println("What is your first name?");
        String firstName = input.nextLine();

        System.out.println("What is your last name?");
        String lastName = input.nextLine();

        String email;
        do {
            System.out.println("What is your email address?");
            email = input.nextLine();
            if (!regex.asMatchPredicate().test(email)) {
                System.out.println("Please enter a valid email address.");
            }
        } while (!regex.asMatchPredicate().test(email));

        player.hasRegistered = true;
        player.firstName = firstName;
        player.lastName = lastName;
        player.email = email;

        System.out.println("Welcome " + firstName + " " + lastName + ", with email " + email);
    }
}
