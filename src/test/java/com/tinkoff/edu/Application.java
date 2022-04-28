package com.tinkoff.edu;

import com.tcs.edu.domain.Message;
import com.tcs.edu.printer.MessageService;
import static com.tcs.edu.enums.Severity.*;
import static com.tcs.edu.enums.MessageOrder.*;
import static com.tcs.edu.enums.Doubling.*;

class Application {
    public static void main(String[] args) {
        MessageService callMessageService = new MessageService();
        Message message1 = new Message(DESC, DISTINCT, MINOR,"Hello World!", "22","22","33", "Hello World!");
        Message message2 = new Message(ASC, DOUBLES, REGULAR, "Hello World!", "444", "555","555");
        Message message3 = new Message(ASC, REGULAR, "Hello World!");
        Message message4 = new Message(MAJOR, "Hello World!");
        Message message5 = new Message("Hello World!");
        callMessageService.log(message1);
        callMessageService.log(message2);
        callMessageService.log(message3);
        callMessageService.log(message4);
        callMessageService.log(message5);
    }
}