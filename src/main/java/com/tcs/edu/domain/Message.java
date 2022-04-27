package com.tcs.edu.domain;

import com.tcs.edu.enums.Severity;

public class Message {
    private Severity level;
    private String[] body;

    public Message(Severity level, String... body){
        if(body == null){System.out.print("Please, add at least one message!\n");}
        this.level = level;
        this.body = body;
    }

    public Message(String... body){
        this(Severity.MINOR, body);
    }

    public Severity getLevel() {
        return level;
    }
    public String[] getBody() {
        return body;
    }

    public void setLevel(Severity level) {
        this.level = level;
    }

    public void setBody(String[] body) {
        this.body = body;
    }
}
