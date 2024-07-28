package com.thomasnolan;

import org.junit.jupiter.api.BeforeAll; 
import org.junit.jupiter.api.BeforeEach; 
import org.junit.jupiter.api.AfterEach; 
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.thomasnolan.RegisterSquare;
import com.thomasnolan.Square;

import java.util.Locale;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class SquareTest {

    @Test
    void validEmail() {

        Square square = new Square(null);

        boolean result = square.isValidEmail("test@example.com");
        
        assertEquals(true, result);
    }

    @Test
    void invalidEmail() {

        Square square = new Square(null);

        boolean result = square.isValidEmail("BAD_EMAIL");
        
        assertEquals(false, result);
    }

}