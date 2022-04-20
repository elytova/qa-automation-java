package com.tcs.edu.printer;

import com.tcs.edu.decorator.Severity;
import static com.tcs.edu.decorator.PageDecorator.*;
import static com.tcs.edu.decorator.SeverityDecorator.decorate;

public class MessageService {

    /**
     * this class forms a final message for printing like a constructor: using message decorators
     * @param severity - severity sent from main class
     * @param message - message sent from main class
     */

    public static void print(Severity severity, String message, String... messages) {

        String prefixMessage = decorateWithPage();
        String severityMessage = decorate(severity);
        ConsolePrinter.printMessage(String.format(
                "[%s] %s %s (%s) %s",
                messageCount, currentTime, message, severityMessage, prefixMessage));

        if (messages != null){
            for (String text : messages) {
                if(text != null) {
                    String prefixMessage1 = decorateWithPage();
                    ConsolePrinter.printMessage(String.format(
                            "[%s] %s %s (%s) %s",
                            messageCount, currentTime, text, severityMessage, prefixMessage1));
                }
            }
        }
    }
}