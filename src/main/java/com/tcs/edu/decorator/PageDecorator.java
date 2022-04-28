package com.tcs.edu.decorator;

public class PageDecorator {

    /**
     * this class divides messages into pages based on page size
     * side effect on a global variables messageCount and PAGE_SIZE
     */

    public static Integer messageCount = 0;
    public static final Integer PAGE_SIZE = 2;

    public String decorateWithPage(){
        ++messageCount;
        if (messageCount % PAGE_SIZE == 0 && messageCount != 0) {
            return "\n---";
        }
        return "";
    }
}