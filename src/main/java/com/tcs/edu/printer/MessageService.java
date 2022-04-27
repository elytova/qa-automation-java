package com.tcs.edu.printer;

import com.tcs.edu.domain.Message;
import com.tcs.edu.enums.Doubling;
import com.tcs.edu.enums.MessageOrder;

import java.util.Objects;

import static com.tcs.edu.decorator.PageDecorator.*;
import static com.tcs.edu.decorator.SeverityDecorator.decorate;
import static java.util.Arrays.copyOf;

public class MessageService {

    /**
     * this class forms a final message for printing like a constructor: using message decorators
     * overloaded function log() - prints decorated messages
     *
     * @param message - objects sent from main class. Contains severity level and array of messages
     */

    public static void log(Message message) {
        if (message.getBody() != null) {
            String severityMessage = decorate(message.getLevel());
            for (String text : message.getBody()) {
                if (text != null) {
                    String prefixMessage = decorateWithPage();
                    ConsolePrinter.printMessage(String.format(
                            "[%s] %s %s (%s) %s",
                            messageCount, currentTime, text, severityMessage, prefixMessage));
                }
            }
        }
    }

    public static void log(MessageOrder order, Message message) {
        if (message.getBody() != null && order == MessageOrder.DESC) {
            String[] newBody = new String[message.getBody().length];
            int i = 0;
            for (int j = message.getBody().length - 1; j >= 0; j--) {
                newBody[i] = message.getBody()[j];
                i++;
            }
            message.setBody(newBody);
        }
        log(message);
    }

    public static void log(MessageOrder order, Doubling doubling, Message message) {
            if (message.getBody() != null && doubling == Doubling.DISTINCT) {
                int messageLength = message.getBody().length;
                int actualSize = 0;
                String[] newMessageArray = new String[messageLength];
                for (String currentMessage : message.getBody()) {
                    boolean exists = false;
                    for (int j = 0; j < actualSize; j++) {
                        if (Objects.equals(currentMessage, newMessageArray[j])) {
                            exists = true;
                            break;
                        }
                    }
                    if (!exists) {
                        newMessageArray[actualSize] = currentMessage;
                        actualSize++;
                    }
                }
                String[] messagesBody = copyOf(newMessageArray, actualSize);
                message.setBody(messagesBody);
            }
            log(order, message);
    }

}
