package com.tcs.edu.domain;

import java.util.HashMap;
import java.util.UUID;

public class HashMapMessageRepository implements MessageRepository {
    private final HashMap<UUID, Message> messages = new HashMap<>();

    @Override
    public UUID create(Message message) {
        UUID primaryKey = UUID.randomUUID();
        messages.put(primaryKey,message);
        return primaryKey;
    }

    public Message findByPrimaryKey(UUID primaryKey){
        return messages.get(primaryKey);
    }
}
