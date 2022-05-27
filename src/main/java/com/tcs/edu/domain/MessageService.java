package com.tcs.edu.domain;

import java.util.Collection;
import java.util.UUID;

public interface MessageService{
    Message log(Message messageForPrint);
    Message findByPrimaryKey(UUID key);
    Collection<Message> findAll();
}
