package com.tinkoff.edu;

import com.tcs.edu.domain.Message;
import com.tcs.edu.domain.MessageService;
import com.tcs.edu.printer.OrderedDistinctedMessageService;
import static com.tcs.edu.enums.Severity.*;
import static com.tcs.edu.enums.MessageOrder.*;
import static com.tcs.edu.enums.Doubling.*;

class Application {
    public static void main(String[] args) {
        Message message1 = new Message(DESC, DISTINCT, MINOR,"Hello World!", "22","22","33", "Hello World!");
        Message message2 = new Message(ASC, DOUBLES, REGULAR, "Hello World!", "444", "555","555");
        Message message3 = new Message(ASC, REGULAR, "Hello World!");
        Message message4 = new Message(MAJOR, "Hello World!");
        Message message5 = new Message("Hello World!");

        MessageService service = new OrderedDistinctedMessageService();
        service.log(message1);
        service.log(message2);
        service.log(message3);
        service.log(message4);
        service.log(message5);
    }
}