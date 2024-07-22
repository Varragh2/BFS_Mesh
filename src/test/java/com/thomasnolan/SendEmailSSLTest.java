package com.thomasnolan;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.thomasnolan.SendEmailSSL;

import java.beans.Transient;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class SendEmailSSLTest {


    @Test
    void emailPropsSetCorrectly(){
        SendEmailSSL emailSender = new SendEmailSSL();

        assertAll(
            "Grouped Assertions of Email Properties",
            () -> assertEquals("smtp.gmail.com", emailSender.props.getProperty("mail.smtp.host"), "Host should be Gmail"),
            () -> assertEquals("465", emailSender.props.getProperty("mail.smtp.port"), "Port should be SSL (465)"),
            () -> assertEquals("true", emailSender.props.getProperty("mail.smtp.auth"), "Authorization should be true")
          );

    }

    @Test
    void sendSSLEmail() {

        String recipients = "nolant190@gmail.com, shea.nolan@gmail.com";
        //String recipients = "shea.nolan@gmail.com";
        String subject = "Test Email from " + System.currentTimeMillis();

        SendEmailSSL emailSender = new SendEmailSSL();
        Boolean result = emailSender.send(recipients,subject, "This is a test!");

        assertEquals(true,result);
    }

}