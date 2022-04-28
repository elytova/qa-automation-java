package com.tcs.edu.printer;

import com.tcs.edu.decorator.OrderedDistinctMessageService;
import com.tcs.edu.decorator.PageDecorator;
import com.tcs.edu.decorator.SeverityDecorator;
import com.tcs.edu.domain.Message;
import static com.tcs.edu.decorator.PageDecorator.messageCount;
import static com.tcs.edu.decorator.TimestampMessageDecorator.currentTime;

public class MessageService implements com.tcs.edu.domain.MessageService {

    /**
     * this class forms a final message for printing like a constructor: using message decorators
     * function log() call Decorators and prints decorated messages
     */

    ConsolePrinter printer = new ConsolePrinter();
    PageDecorator decorator = new PageDecorator();
    OrderedDistinctMessageService messageDecorator = new OrderedDistinctMessageService();
    SeverityDecorator severityDecorator = new SeverityDecorator();

    public void log(Message message) {

        if (message.getBody() != null) {
            messageDecorator.checkForDuplicates(message);
            messageDecorator.checkForOrder(message);
            String severityMessage = severityDecorator.decorate(message);
            for (String text : message.getBody()) {
                if (text != null) {
                    String prefixMessage = decorator.decorateWithPage();
                    printer.printMessage(String.format(
                            "[%s] %s %s (%s) %s",
                            messageCount, currentTime, text, severityMessage, prefixMessage));
                }
            }
        }
    }
}
