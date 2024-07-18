package com.thomasnolan;

import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;

import java.util.regex.Pattern;


public class ReferFriendSquare extends Square {

    //https://stackoverflow.com/questions/8204680/java-regex-email
    Pattern regex = Pattern.compile("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b");


    @Override
    public void run(Player player) {
        System.out.println("Welcome to Refer a Friend.\nDo you want to refer a friend? (y/n)");
        if (!Game.readYesNo()) {
            System.out.println("That's too bad.");
            return;
        }
        System.out.println("What is your friend's first name?");
        Game.readInput();

        System.out.println("What is your friend's last name?");
        Game.readInput();

        String friendEmail;
        do {
            System.out.println("What is your friend's email address?");
            friendEmail = Game.readInput();
            if (!regex.asMatchPredicate().test(friendEmail)) {
                System.out.println("Please enter a valid email address.");
            }
        } while (!regex.asMatchPredicate().test(friendEmail));

        Email email = EmailBuilder.startingBlank()
                .to("Thomas Nolan", "nolant190@gmail.com")
                .withSubject("hey")
                .withPlainText("Please this email works!")
                .buildEmail();

        Mailer mailer = MailerBuilder
                .buildMailer();

        mailer.sendMail(email);


        player.mesh_bucks += 100;

        System.out.println("Welcome " + player.firstName + " " + player.lastName + ", with email " + email);
        System.out.println("Thanks for registering your friend! You earned 100 mesh bucks");
    }
}
