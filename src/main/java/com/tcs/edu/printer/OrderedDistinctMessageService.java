package com.tcs.edu.printer;

import com.tcs.edu.domain.*;
import com.tcs.edu.decorator.*;
import com.tcs.edu.enums.Severity;
import com.tcs.edu.validator.ValidatedService;

import java.util.Collection;
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
            OrderDecorator orderDecorator,
            DuplicatesDecorator duplicatesDecorator,
            SeverityDecorator severityDecorator,
            ConsolePrinter consolePrinter) {
        this.pageDecorator = pageDecorator;
        this.orderDecorator = orderDecorator;
        this.duplicatesDecorator = duplicatesDecorator;
        this.severityDecorator = severityDecorator;
        this.consolePrinter = consolePrinter;
    }

    private final PageDecorator pageDecorator;
    private final OrderDecorator orderDecorator;
    private final DuplicatesDecorator duplicatesDecorator;
    private final SeverityDecorator severityDecorator;
    private final ConsolePrinter consolePrinter;
    MessageRepository messageRepository = new HashMapMessageRepository();

    public Message log(Message message) {
        String severityMessage = "";
        try {
            super.isArgsValid(message);
        } catch (IllegalArgumentException e) {
            throw new LogException(NOT_VALID_ARG_MESSAGE, e);
        }

            if (orderDecorator != null) {
                orderDecorator.decorate(message);
            }

            if (duplicatesDecorator != null) {
                duplicatesDecorator.decorate(message);
            }

            if (severityDecorator != null) {
                severityMessage = severityDecorator.decorate(message);
            }

            if (consolePrinter != null) {
                String[] decoratedMessageArray = new String[message.getBody().length];
                for(int i = 0; i < message.getBody().length; i ++){
                    if (message.getBody()[i] != null) {
                        String prefixMessage = pageDecorator.decorateWithPage();
                        String decoratedMessage = String.format("%s (%s)", message.getBody()[i], severityMessage);
                        decoratedMessageArray[i] = decoratedMessage;

                        //если понадобится печатать/проверять с датой:
                        String finalDecoratedMessage = String.format("[%s] %s %s %s",
                                messageCount, currentTime, decoratedMessage, prefixMessage);
                    }
                }
                message.setBody(decoratedMessageArray);
                message.setId(messageRepository.create(message));
            }
            return message;
        }

    @Override
    public Message findByPrimaryKey(UUID key) {
        return messageRepository.findByPrimaryKey(key);
    }

    @Override
    public Collection<Message> findAll() {
        return messageRepository.findAll();
    }

    @Override
    public Collection<Message> findBySeverity(Severity by) {
        return messageRepository.findBySeverity(by);
    }
}
