package com.tinkoff.edu;

import com.tcs.edu.domain.Message;
import com.tcs.edu.printer.MessageService;
import static com.tcs.edu.enums.Severity.*;
import static com.tcs.edu.enums.MessageOrder.*;
import static com.tcs.edu.enums.Doubling.*;

class Application {
    public static void main(String[] args) {
        Message message1 = new Message(MINOR,"Hello World!", "22","22","33", "Hello World!");
        Message message2 = new Message(REGULAR, "Hello World!", "444", "555","555");
        Message message3 = new Message(REGULAR, "Hello World!");
        Message message4 = new Message(MAJOR, "Hello World!");
//        Message message5 = new Message(MAJOR, null);
        Message message6 = new Message("Hello World!");
        MessageService.log(DESC, DISTINCT, message1);
        MessageService.log(ASC, DOUBLES, message2);
        MessageService.log(ASC, message3);
        MessageService.log(message4);
//        MessageService.log(message5);
        MessageService.log(message6);
    }
}