package com.tcs.edu.printer;

import com.tcs.edu.domain.Printer;

public class ConsolePrinter implements Printer {

    /**
     * this class prints incoming parameters (string) in Console
     * method 'print' prints message in Console. This method doesn't have no any side effects
     * @param message - String you want to print
     * @author e.lytova
     */

    public void printMessage(String message){
        System.out.println(message);
    }
}