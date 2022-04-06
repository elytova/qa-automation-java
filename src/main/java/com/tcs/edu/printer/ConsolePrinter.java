package com.tcs.edu.printer;

import com.tcs.edu.decorator.TimestampMessageDecorator;

public class ConsolePrinter {

    /**
     * this class prints incoming parameters (string) in Console
     * method 'print' prints message in Console. This method doesn't have no any side effects
     * side effect on a global variable (messageCount)
     * @param message - String you want to print
     * @author e.lytova
     */

    public static void print(String message) {
        System.out.println("[" + ++TimestampMessageDecorator.messageCount + "] " + message);
        System.out.println("[" + ++TimestampMessageDecorator.messageCount + "] " + message);
        System.out.println("[" + ++TimestampMessageDecorator.messageCount + "] " + message);
    }
}