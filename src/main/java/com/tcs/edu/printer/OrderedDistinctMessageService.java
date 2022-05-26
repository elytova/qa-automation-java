package com.tcs.edu.printer;

import com.tcs.edu.domain.*;
import com.tcs.edu.decorator.*;
import com.tcs.edu.validator.ValidatedService;

import java.util.UUID;

import static com.tcs.edu.decorator.PageDecorator.messageCount;
import static com.tcs.edu.decorator.TimestampMessageDecorator.currentTime;
import static com.tcs.edu.domain.LogException.NOT_VALID_ARG_MESSAGE;

public class OrderedDistinctMessageService extends ValidatedService implements MessageService {

    /**
     * this class forms a final message for printing like a constructor: using message decorators
     * function log() call Decorators and prints decorated messages
     */

    public OrderedDistinctMessageService(
            PageDecorator pageDecorator,
            DuplicatesDecorator duplicatesDecorator,
            OrderDecorator orderDecorator,
            SeverityDecorator severityDecorator,
            ConsolePrinter consolePrinter) {
        this.pageDecorator = pageDecorator;
        this.duplicatesDecorator = duplicatesDecorator;
        this.orderDecorator = orderDecorator;
        this.severityDecorator = severityDecorator;
        this.consolePrinter = consolePrinter;
    }

    private final PageDecorator pageDecorator;
    private final DuplicatesDecorator duplicatesDecorator;
    private final OrderDecorator orderDecorator;
    private final SeverityDecorator severityDecorator;
    private final ConsolePrinter consolePrinter;
    private MessageRepository messageRepository = new HashMapMessageRepository();

    public void log(Message message) {
        String severityMessage = "";
        try {
            super.isArgsValid(message);
        } catch (IllegalArgumentException e) {
            throw new LogException(NOT_VALID_ARG_MESSAGE, e);
        }

            if (duplicatesDecorator != null) {
                duplicatesDecorator.decorate(message);
            }

            if (orderDecorator != null) {
                orderDecorator.decorate(message);
            }

            if (severityDecorator != null) {
                severityMessage = severityDecorator.decorate(message);
            }

            if (consolePrinter != null) {
                for (String text : message.getBody()) {
                    if (text != null) {
                        String prefixMessage = pageDecorator.decorateWithPage();
                        String messageForPrint = String.format(
                                "[%s] %s %s (%s) %s",
                                messageCount, currentTime, text, severityMessage, prefixMessage);
                        final UUID primaryKey = messageRepository.create(messageForPrint);
                        message.setId(primaryKey);
                        System.out.println(primaryKey + "===" + (messageRepository.findByPrimaryKey(primaryKey)));
                    }

                }
            }
        }
}
