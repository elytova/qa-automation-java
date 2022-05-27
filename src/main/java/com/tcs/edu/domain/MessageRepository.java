package com.tcs.edu.domain;
import com.tcs.edu.enums.Severity;

import java.util.Collection;
import java.util.UUID;

public interface MessageRepository {
    UUID create(Message message);

    Message findByPrimaryKey(UUID primaryKey);

    Collection<Message> findAll();

    Collection<Message> findBySeverity(Severity by);
}
