package com.tcs.edu.domain;

import java.util.HashMap;
import java.util.UUID;

public class HashMapMessageRepository implements MessageRepository {
    private final HashMap<UUID, String> messages = new HashMap<>();

    @Override
    public UUID create(String messageForPrint) {
        UUID primaryKey = UUID.randomUUID();
        messages.put(primaryKey,messageForPrint);
        return primaryKey;
    }

    public Object findByPrimaryKey(UUID primaryKey){
        return messages.get(primaryKey);
    }
}
