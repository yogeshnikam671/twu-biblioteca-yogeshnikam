package com.twu.biblioteca;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static com.twu.biblioteca.Printer.*;
import static org.junit.jupiter.api.Assertions.*;

class PrinterTest {
    private PrintStream sysOut;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    Printer printer = new Printer();

    @BeforeEach
    public void setUpStreams() {
        sysOut = System.out;
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void revertStreams() {
        System.setOut(sysOut);
    }

    @Test
    void shouldBeAbleToPrintAString() {
        String expected = "Hello !\n";

        printer.print("Hello !");

        assertEquals(expected, outContent.toString());
    }

    @Test
    void shouldBeAbleToPrintAnInteger() {
        String expected = "1";

        printer.print(1);

        assertEquals(expected, outContent.toString());
    }

    @Test
    void shouldBeAbleToPrintANewLineWhenNoArgumentsGiven() {
        String expected = "\n";

        printer.print();

        assertEquals(expected, outContent.toString());
    }
}