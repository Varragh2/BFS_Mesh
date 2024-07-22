package com.thomasnolan;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.io.IOException;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

//import com.sun.jdi.connect.Transport;

public class SendEmailSSL {

    public Properties props = new Properties();
    private Properties credentialProps = new Properties();

    private static String EMAIL_PROPERTIES = "email.properties";
    private static String CREDENTIAL_PROPERTIES = "credential.properties";

    /*
     * 
     */
    public SendEmailSSL() {

        loadEmailProps();
        loadCredentialProps();

    }    

    private void loadEmailProps(){

        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        
        try (InputStream resourceStream = loader.getResourceAsStream(EMAIL_PROPERTIES)){
            props.load(resourceStream);    
            System.out.println(props);
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

    public Boolean send(String recipients, String subject, String body) {

        final String username = credentialProps.getProperty("username");
        final String password = credentialProps.getProperty("password");
        System.out.println("Set username and password");
        
        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            System.out.println("After authentication");

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(recipients)
            );
            message.setSubject(subject);
            message.setText(body);

            System.out.println("Before email sent");
            Transport.send(message);

            System.out.println("Sent message successfully....");            
            return true;

        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

}