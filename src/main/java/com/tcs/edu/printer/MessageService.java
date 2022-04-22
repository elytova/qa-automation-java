package com.tcs.edu.printer;

import com.tcs.edu.enums.Doubling;
import com.tcs.edu.enums.Severity;
import com.tcs.edu.enums.MessageOrder;

import java.util.Arrays;
import java.util.Objects;

import static com.tcs.edu.decorator.PageDecorator.*;
import static com.tcs.edu.decorator.SeverityDecorator.decorate;

public class MessageService {

    /**
     * this class forms a final message for printing like a constructor: using message decorators
     *
     * @param level   - severity sent from main class
     * @param message - message sent from main class
     */
    public static void print(Severity level, String message, String... messages) {

        String prefixMessage = decorateWithPage();
        String severityMessage = decorate(level);
        ConsolePrinter.printMessage(String.format(
                "[%s] %s %s (%s) %s",
                messageCount, currentTime, message, severityMessage, prefixMessage));

        if (messages != null) {
            for (String text : messages) {
                if (text != null) {
                    String prefixMessage1 = decorateWithPage();
                    ConsolePrinter.printMessage(String.format(
                            "[%s] %s %s (%s) %s",
                            messageCount, currentTime, text, severityMessage, prefixMessage1));
                }
            }
        }
    }

    public static void print(Severity level, MessageOrder order, String message, String... messages) {
        if (messages != null && order == MessageOrder.DESC) {
            int i = 0;
            int j = messages.length - 1;
            String tmp;
            while (j > i) {
                tmp = messages[j];
                messages[j] = messages[i];
                messages[i] = tmp;
                j--;
                i++;
            }
        }
        print(level, message, messages);

    }

    public static void print(Severity level, MessageOrder order, Doubling doubling, String message, String... messages) {
        if (messages != null && doubling == Doubling.DISTINCT) {
            int messageLength = messages.length;
            String[] newMessageArray = new String[messageLength];
            int actualSize = 0;
            for (String currentMessage : messages) {
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
            messages = Arrays.copyOf(newMessageArray, actualSize);
        }
        print(level, order, message, messages);
    }
}
