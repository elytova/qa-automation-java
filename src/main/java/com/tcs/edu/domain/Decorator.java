package com.tcs.edu.domain;

public interface Decorator {
    Message checkForDuplicates(Message messageForPrint);
    Message checkForOrder(Message messageForPrint);
}
