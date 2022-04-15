package com.tcs.edu.decorator;

public class SeverityDecorator {

    /**
     * this class indicates what text is equals to incoming severity
     * (in other words, class transforms severity to text you can use in output message)
     * @param severity - severity of message. On the basis this param, specified in this class text will be printed
     * this class doesn't have any side effects.
     */

    public static String decorate(Severity severity){
        String severityString = null;
        switch (severity){
            case MINOR: severityString = ""; break;
            case REGULAR: severityString = "!"; break;
            case MAJOR: severityString = "!!!"; break;
        }
        return severityString;
    }
}
