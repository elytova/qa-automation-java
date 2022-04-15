package com.tcs.edu.printer;

import com.tcs.edu.decorator.Severity;

import static com.tcs.edu.decorator.PageDecorator.*;
import static com.tcs.edu.decorator.SeverityDecorator.decorate;

public class MessageService {

    /**
     * this class forms a final message for printing like a constructor: using message decorators
     * @param severity - severity sent from main class
     * @param message - message sent from main class
     * this method doesn't have any side effects.
     */

    public static String print(Severity severity, String message){
        String prefixMessage = decorateWithPage();
        String severityMessage = decorate(severity);
        return String.format(
                "[%s] %s %s (%s) %s",
                messageCount, currentTime, message, severityMessage, prefixMessage
        );
    }
}
