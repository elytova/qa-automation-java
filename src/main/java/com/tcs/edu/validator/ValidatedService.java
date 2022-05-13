package com.tcs.edu.validator;

public abstract class ValidatedService {

    public boolean isArgsValid(String[] body){
        if(body == null) return false;
        return body.length != 0;
    }
}
