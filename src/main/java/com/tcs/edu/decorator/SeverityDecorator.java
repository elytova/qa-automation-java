package com.tcs.edu.decorator;

import com.tcs.edu.domain.Message;

public class SeverityDecorator {

    /**
     * this class indicates what text is equals to incoming severity
     * (in other words, class transforms severity to text you can use in output message)
     * @param message - using here only severity of message. On the basis this param, specified in this class text will be printed
     * this class doesn't have any side effects.
     */

    public String decorate(Message message){
        String severityString = null;
        switch (message.getLevel()){
            case MINOR: severityString = ""; break;
            case REGULAR: severityString = "!"; break;
            case MAJOR: severityString = "!!!"; break;
        }

        return severityString;
    }
}
