package com.tcs.edu.decorator;

import java.time.Instant;

public class PageDecorator {

    /**
     * this class divides messages into pages based on page size
     * side effect on a global variables messageCount and PAGE_SIZE
     */

    public static String currentTime = Instant.now().toString();
    public static Integer messageCount = 0;
    public static final Integer PAGE_SIZE = 2;

    public static String decorateWithPage(){
        ++messageCount;
        if (messageCount % PAGE_SIZE == 0 && messageCount != 0) {
            return "\n---";
        }
        return "";
    }
}