package com.tcs.edu.domain;

import java.util.UUID;

public interface MessageRepository {
    UUID create(String messageForPrint);

    Object findByPrimaryKey(UUID primaryKey);
}
