package com.tcs.edu.printer;

import com.tcs.edu.decorator.Severity;
import static com.tcs.edu.printer.MessageService.print;

public class ConsolePrinter {

    /**
     * this class prints incoming parameters (string) in Console
     * method 'print' prints message in Console. This method doesn't have no any side effects
     * @param message - String you want to print
     * @author e.lytova
     */

    public static void printMessage(Severity severity, String message){
        String finalMessage = print(severity, message);
        System.out.println(finalMessage);
    }
}