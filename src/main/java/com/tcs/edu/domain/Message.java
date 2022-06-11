package com.tcs.edu.domain;

import com.tcs.edu.enums.Doubling;
import com.tcs.edu.enums.MessageOrder;
import com.tcs.edu.enums.Severity;

import java.util.Arrays;
import java.util.UUID;

public class Message{
    private Severity level;
    private String[] body;
    private MessageOrder order;
    private Doubling doubling;
    private UUID id;

    public Message(MessageOrder order, Doubling doubling, Severity level, String... body){
        this.level = level;
        this.body = body;
        this.order = order;
        this.doubling = doubling;
    }
    public Message(MessageOrder order, Severity level, String... body){
        this(order, Doubling.DOUBLES, level, body);
    }

    public Message(MessageOrder order, String... body){
        this(order, Doubling.DOUBLES, Severity.MINOR, body);
    }

    public Message(Severity level, String... body){
        this(MessageOrder.ASC, Doubling.DOUBLES, level, body);
    }
    
    public Message(String... body){
        this(MessageOrder.ASC, Doubling.DOUBLES, Severity.MINOR, body);
    }

    public Message(MessageOrder order, Doubling doubling, String... body) { this(order, doubling, Severity.MINOR, body);
    }

    public Message(Doubling doubling, String... body) { this(MessageOrder.ASC, doubling, Severity.MINOR, body);
    }
  
    public Severity getLevel() {
        return level;
    }
    public String[] getBody() {
        return body;
    }
    public MessageOrder getOrder() { return order; }
    public Doubling getDoubling() { return doubling; }

    public void setBody(String[] body) {
        this.body = body;
    }

    public UUID getId() {return id;}

    public void setId(UUID id) {this.id = id;}

    @Override
    public String toString() {
        return "Message{" +
                "body=" + Arrays.toString(body) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message)) return false;
        Message message = (Message) o;
        return Arrays.equals(getBody(), message.getBody());
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(getBody());
    }
}
