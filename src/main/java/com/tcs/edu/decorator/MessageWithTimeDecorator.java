package com.tcs.edu.decorator;

import java.time.Instant;

public class MessageWithTimeDecorator {

    /**
     * this class changes an incoming message
     * method 'addTimetoMessage' adds current time to the beggining of the message. This method doesn't have no any side effects
     */

    public static String CurrentTime = Instant.now().toString();

    public static String addTimetoMessage(String message) {
        return CurrentTime + ": " + message;
    }
}
