package com.tcs.edu.decorator;

import com.tcs.edu.domain.MessageDecorator;
import com.tcs.edu.domain.Message;
import com.tcs.edu.enums.Doubling;
import com.tcs.edu.enums.MessageOrder;

import java.util.Objects;

import static java.util.Arrays.copyOf;

public class OutputOderDecorator implements MessageDecorator {


    /**
     * this class decorates messages based on incoming DOUBLING and MESSAGEORDER enums
     * no any side effect on a global variables
     */
    @Override
    public Message checkForDuplicates(Message message) {
        if (message.getDoubling() == Doubling.DISTINCT) {
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
        return message;
    }

    public Message checkForOrder(Message message) {
        if (message.getOrder() == MessageOrder.DESC) {
            String[] newBody = new String[message.getBody().length];
            int i = 0;
            for (int j = message.getBody().length - 1; j >= 0; j--) {
                newBody[i] = message.getBody()[j];
                i++;
            }
            message.setBody(newBody);
        }
        return message;
    }
}
