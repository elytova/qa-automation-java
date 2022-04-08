package com.tcs.edu.decorator;

import java.time.Instant;

public class TimestampMessageDecorator {

    /**
     * this class changes an incoming message
     * method 'TimestampMessageDecorator' adds current time to the beginning of the message.
     * this method doesn't have any side effects.
     */


    public static String currentTime = Instant.now().toString();
    public static Integer messageCount = 0;
    public static final Integer PAGE_SIZE = 2;

    public static String decorate(String message) {
        ++messageCount;
        var decoratedMessage = "[" + messageCount + "] " + currentTime + ": " + message;
        if (messageCount % PAGE_SIZE == 0 && messageCount != 0) {
            return decoratedMessage + "\n" +  "---";
        }
        return decoratedMessage;
    }
}