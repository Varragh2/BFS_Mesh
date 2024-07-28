package com.thomasnolan;

/**
 * Enables player to refer a friend in order to gain more mesh bucks
 */
public class ReferFriendSquare extends Square {

    /**
     * {@inheritDoc}
     */
    public ReferFriendSquare(GameIOHandler io){
        super(io);
    }

    /**
     * Prompts player to see if they want to refer a friend and asks for friend name and email.
     * Sends email to friend welcoming them to the community.
     * Adds 100 mesh bucks to player account.
     */
    @Override
    public boolean run(Player player) {
        
        boolean endGame = false;

        player.mesh_bucks += 100;
        
        super.io.printLine("refer_friend_prompt");
        if (!super.io.readYesNo()) {
            super.io.printLine("leave_prompt");
            return false;
        }
        super.io.printLine("refer_friend_first_name");
        String friendFirstName = super.io.readInput(endGame);

        super.io.printLine("refer_friend_last_name");
        String friendLastName = super.io.readInput(endGame);

        String friendEmail;
        super.io.printLine("refer_friend_email");
        friendEmail = super.io.readInput(endGame);

        // Validate email
        if (!super.isValidEmail(friendEmail)) {
            super.io.printLine("refer_friend_email_fail");
        }

        //Send Email!!
        String subject = super.io.getString("welcome_email_subject");
        String body = super.io.getString("welcome_email_body", friendFirstName, friendLastName, player.firstName, player.lastName);

        SendEmailSSL emailSender = new SendEmailSSL();
        Boolean result = emailSender.send(friendEmail, subject, body);

        super.io.printLine("refer_friend_success", friendFirstName, friendLastName, friendEmail);

        return true;
    }
}
