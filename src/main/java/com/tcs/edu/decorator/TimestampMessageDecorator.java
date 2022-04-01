package com.tcs.edu.decorator;

import java.time.Instant;

public class TimestampMessageDecorator {

    /**
     * this class changes an incoming message
     * method 'TimestampMessageDecorator' adds current time to the beginning of the message.
     * this method doesn't have any side effects
     */

    public static Integer messageCount = 1;
    public static String currentTime = Instant.now().toString();

    public static String decorate(String message) {
        String decoratedMessage = currentTime + ": " + message;
        return decoratedMessage;
    }
}