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
        Game.printMessage("refer_friend_prompt");
        if (!Game.readYesNo()) {
            Game.printMessage("leave_prompt");
            return;
        }
        Game.printMessage("refer_friend_first_name");
        Game.readInput();

        Game.printMessage("refer_friend_last_name");
        Game.readInput();

        String friendEmail;
        do {
            Game.printMessage("refer_friend_email");
            friendEmail = Game.readInput();
            if (!regex.asMatchPredicate().test(friendEmail)) {
                Game.printMessage("refer_friend_email_fail");
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

        Game.printMessage("refer_friend_success", player.firstName, player.lastName, player.email);
    }
}
