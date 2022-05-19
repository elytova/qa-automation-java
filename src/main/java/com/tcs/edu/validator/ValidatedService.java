package com.tcs.edu.validator;

import com.tcs.edu.domain.Message;

public abstract class ValidatedService {

    public void isArgsValid(Message message){
        if(message.getBody() == null) throw new IllegalArgumentException("body is null");
        if(message.getBody().length == 0) throw new IllegalArgumentException("body is empty");
    }
}
