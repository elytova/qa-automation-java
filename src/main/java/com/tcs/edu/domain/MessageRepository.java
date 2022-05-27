package com.tcs.edu.domain;

import java.util.UUID;

public interface MessageRepository {
    UUID create(Message message);

    Message findByPrimaryKey(UUID primaryKey);
}
