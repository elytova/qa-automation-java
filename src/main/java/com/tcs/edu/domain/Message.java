package com.tcs.edu.domain;

import com.tcs.edu.enums.Doubling;
import com.tcs.edu.enums.MessageOrder;
import com.tcs.edu.enums.Severity;
import com.tcs.edu.validator.ValidatedService;

public class Message extends ValidatedService {
    private Severity level;
    private String[] body;
    private MessageOrder order;
    private Doubling doubling;

    public Message(MessageOrder order, Doubling doubling, Severity level, String... body){
        if(!super.isArgsValid(body)){System.out.print("Please, add at least one message!\n");}
        this.level = level;
        this.body = body;
        this.order = order;
        this.doubling = doubling;
    }
    public Message(MessageOrder order, Severity level, String... body){
        this(order, Doubling.DOUBLES, level, body);
    }

    public Message(Severity level, String... body){
        this(MessageOrder.ASC, Doubling.DOUBLES, level, body);
    }
    
    public Message(String... body){
        this(MessageOrder.ASC, Doubling.DOUBLES, Severity.MINOR, body);
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
}
