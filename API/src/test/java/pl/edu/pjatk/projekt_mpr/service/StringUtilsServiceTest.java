package pl.edu.pjatk.projekt_mpr.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringUtilsServiceTest {
    StringUtilsService sus;

    @BeforeEach
    public void setUp() {
        this.sus = new StringUtilsService();
    }

    @Test
    public void testToUpperCase() {
        String testString = "test string";

        assertEquals("TEST STRING", sus.toUpperCase(testString));
    }

    @Test
    public void testToUpperCaseWhenNoStringGiven() {
        String testString = "";
        assertEquals("", sus.toUpperCase(testString));
    }

    @Test
    public void testToLowerCaseButCapitalizeFirstLetter() {
        String testString = "TEST STRING";

        assertEquals("Test string", sus.toLowerCaseButCapitalizeFirstLetter(testString));
    }

    @Test
    public void testToLowerCaseButCapitalizeFirstLetterWhenNoStringGiven() {
        String testString = "";
        assertEquals("", sus.toLowerCaseButCapitalizeFirstLetter(testString));
    }
}
