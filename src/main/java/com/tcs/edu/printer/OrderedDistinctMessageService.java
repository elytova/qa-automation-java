package com.tcs.edu.printer;

import com.tcs.edu.decorator.*;
import com.tcs.edu.domain.Message;
import com.tcs.edu.domain.MessageDecorator;
import com.tcs.edu.domain.MessageService;
import com.tcs.edu.domain.Printer;

import static com.tcs.edu.decorator.PageDecorator.messageCount;
import static com.tcs.edu.decorator.TimestampMessageDecorator.currentTime;

public class OrderedDistinctMessageService implements MessageService {

    /**
     * this class forms a final message for printing like a constructor: using message decorators
     * function log() call Decorators and prints decorated messages
     */
    public OrderedDistinctMessageService(
            PageDecorator pageDecorator,
            DuplicatesDecorator duplicatesDecorator,
            OrderDecorator orderDecorator,
            ConsolePrinter consolePrinter,
            SeverityDecorator severityDecorator) {
        this.pageDecorator = pageDecorator;
        this.duplicatesDecorator = duplicatesDecorator;
        this.orderDecorator = orderDecorator;
        this.consolePrinter = consolePrinter;
        this.severityDecorator = severityDecorator;
    }

    private final ConsolePrinter consolePrinter;
    private final PageDecorator pageDecorator;
    private final DuplicatesDecorator duplicatesDecorator;
    private final OrderDecorator orderDecorator;
    private final SeverityDecorator severityDecorator;

    public void log(Message message) {
        String severityMessage = "";

        if (message.getBody() != null) {
            if(duplicatesDecorator != null){
                duplicatesDecorator.decorate(message);
            }

            if(orderDecorator != null){
                orderDecorator.decorate(message);
            }

            if(severityDecorator != null){
                severityMessage = severityDecorator.decorate(message);
            }

            if(consolePrinter != null) {
                for (String text : message.getBody()) {
                    if (text != null) {
                        String prefixMessage = pageDecorator.decorateWithPage();
                        consolePrinter.printMessage(String.format(
                                "[%s] %s %s (%s) %s",
                                messageCount, currentTime, text, severityMessage, prefixMessage));
                    }
                }
            }
        }
    }

}
