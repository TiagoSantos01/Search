package com.tiagosantos.search.infrastructure;

import org.junit.jupiter.api.Test;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StdOutTest {

    @Test
    public void testInfo(){
        ByteArrayOutputStream exitConsole = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(exitConsole));

        StdOut log= new StdOut();

        log.Info("Teste");
        System.setOut(originalOut);
        String exitConsoleString = exitConsole.toString();

        assertEquals("\033[34m[INFO]\033[0m Teste\n",exitConsoleString);
    }
    @Test
    public void testWarn(){
        ByteArrayOutputStream exitConsole = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(exitConsole));

        StdOut log= new StdOut();

        log.Warn("Teste");
        System.setOut(originalOut);
        String exitConsoleString = exitConsole.toString();

        assertEquals("\033[33m[WARN]\033[0m Teste\n",exitConsoleString);
    }
    @Test
    public void testError(){
        ByteArrayOutputStream exitConsole = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(exitConsole));

        StdOut log= new StdOut();

        log.Error("Teste");
        System.setOut(originalOut);
        String exitConsoleString = exitConsole.toString();

        assertEquals("\033[31m[ERROR]\033[0m Teste\n",exitConsoleString);
    }
}