package com.tcs.edu.domain;

import com.tcs.edu.enums.Severity;
import java.util.Collection;
import java.util.UUID;

public interface MessageService{
    Message log(Message messageForPrint);
    Message findByPrimaryKey(UUID key);
    Collection<Message> findAll();
    Collection<Message> findBySeverity(Severity by);
}
