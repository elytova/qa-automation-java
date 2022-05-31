package com.tcs.edu.domain;

import com.tcs.edu.enums.Severity;

import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

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

    @Override
    public Collection<Message> findAll() {
        return messages.values();
    }

    @Override
    public Collection<Message> findBySeverity(Severity by) {
        return messages.values()
                .stream()
                .filter(message -> message.getLevel() == by)
                .collect(toList());
    }
}
