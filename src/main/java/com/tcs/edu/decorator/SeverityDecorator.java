package com.tcs.edu.decorator;

import com.tcs.edu.domain.LogException;
import com.tcs.edu.domain.Message;
import com.tcs.edu.validator.ValidatedService;

import static com.tcs.edu.domain.LogException.NOT_VALID_ARG_MESSAGE;

public class SeverityDecorator extends ValidatedService {

    /**
     * this class indicates what text is equals to incoming severity
     * (in other words, class transforms severity to text you can use in output message)
     * @param message - using here only severity of message. On the basis this param, specified in this class text will be printed
     * this class doesn't have any side effects.
     */

    public String decorate(Message message){
        try {
            super.isArgsValid(message);
        } catch (IllegalArgumentException e) {
            throw new LogException(NOT_VALID_ARG_MESSAGE, e);
        }

        String severityString = null;
        switch (message.getLevel()){
            case MINOR: severityString = ""; break;
            case REGULAR: severityString = "!"; break;
            case MAJOR: severityString = "!!!"; break;
        }

        return severityString;
    }
}
