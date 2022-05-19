package com.tcs.edu.decorator;

import com.tcs.edu.domain.LogException;
import com.tcs.edu.domain.Message;
import com.tcs.edu.domain.MessageDecorator;
import com.tcs.edu.enums.Doubling;
import com.tcs.edu.validator.ValidatedService;

import static com.tcs.edu.domain.LogException.NOT_VALID_ARG_MESSAGE;
import static java.util.Arrays.copyOf;

public class DuplicatesDecorator extends ValidatedService implements MessageDecorator {

    /**
     * this class decorates messages based on incoming DOUBLING and MESSAGEORDER enums
     * no any side effect on a global variables
     */

    @Override
    public Message decorate(Message message){
        try {
            super.isArgsValid(message);
        } catch (IllegalArgumentException e) {
            throw new LogException(NOT_VALID_ARG_MESSAGE, e);
        }

        if (message.getDoubling() == Doubling.DISTINCT) {
            int messageLength = message.getBody().length;
            int actualSize = 0;
            String[] newMessageArray = new String[messageLength];
            for (String currentMessage : message.getBody()) {
                boolean exists = false;
                for (int j = 0; j < actualSize; j++) {
                    if (currentMessage.equals(newMessageArray[j])
                            || currentMessage.hashCode() == newMessageArray[j].hashCode()
                    ) {
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
}
