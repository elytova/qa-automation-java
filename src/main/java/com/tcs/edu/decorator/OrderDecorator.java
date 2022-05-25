package com.tcs.edu.decorator;

import com.tcs.edu.domain.LogException;
import com.tcs.edu.domain.MessageDecorator;
import com.tcs.edu.domain.Message;
import com.tcs.edu.enums.MessageOrder;
import com.tcs.edu.validator.ValidatedService;

import static com.tcs.edu.domain.LogException.NOT_VALID_ARG_MESSAGE;

public class OrderDecorator extends ValidatedService implements MessageDecorator {

    /**
     * this class decorates messages based on incoming DOUBLING and MESSAGEORDER enums
     * no any side effect on a global variables
     */

    @Override
    public Message decorate(Message message) {
        try {
            super.isArgsValid(message);
        } catch (IllegalArgumentException e) {
            throw new LogException(NOT_VALID_ARG_MESSAGE, e);
        }

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
