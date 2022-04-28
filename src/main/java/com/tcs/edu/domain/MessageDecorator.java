package com.tcs.edu.domain;

public interface MessageDecorator {
    Message checkForDuplicates(Message messageForPrint);
    Message checkForOrder(Message messageForPrint);
}
