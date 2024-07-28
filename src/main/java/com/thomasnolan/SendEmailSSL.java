package com.thomasnolan;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.io.IOException;

import java.io.InputStream;
import java.util.Properties;

/**
 * Sends email from game to referred friend via SSL
 */
public class SendEmailSSL {

    public Properties props = new Properties();
    private Properties credentialProps = new Properties();

    private static String EMAIL_PROPERTIES = "email.properties";
    private static String CREDENTIAL_PROPERTIES = "credential.properties";

    /**
     * Reads email and credential settings from resources and initializes SSL connection
     */
    public SendEmailSSL() {

        loadEmailProps();
        loadCredentialProps();

    }    

    private void loadEmailProps(){

        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        
        try (InputStream resourceStream = loader.getResourceAsStream(EMAIL_PROPERTIES)){
            props.load(resourceStream);    
        }
        catch(IOException e) {
            System.out.println(e);
        }    
    }

    private void loadCredentialProps(){

        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        
        try (InputStream resourceStream = loader.getResourceAsStream(CREDENTIAL_PROPERTIES)){
            credentialProps.load(resourceStream);    
        }
        catch(IOException e) {
            System.out.println(e);
        }    
    }

    /**
     * Sends email via SSL connection to recipients
     * @param recipients the addresses to receive the email
     * @param subject the localized email subject
     * @param body the localixed email body
     * @return true if successful, false otherwise
     */
    public Boolean send(String recipients, String subject, String body) {

        final String username = credentialProps.getProperty("username");
        final String password = credentialProps.getProperty("password");
        
        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(recipients)
            );
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);

            System.out.println("Sent message successfully....");            
            return true;

        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

}