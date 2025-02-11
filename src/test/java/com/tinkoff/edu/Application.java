package com.tinkoff.edu;

import com.tcs.edu.decorator.*;
import com.tcs.edu.domain.*;
import com.tcs.edu.printer.ConsolePrinter;
import com.tcs.edu.printer.OrderedDistinctMessageService;

import static com.tcs.edu.enums.Severity.*;
import static com.tcs.edu.enums.MessageOrder.*;
import static com.tcs.edu.enums.Doubling.*;

class Application {
    public static void main(String[] args) {

        MessageService service = new OrderedDistinctMessageService(
                new PageDecorator(),
                new OrderDecorator(),
                new DuplicatesDecorator(),
                new SeverityDecorator(),
                new ConsolePrinter()
        );
        Message message1 = service.log(new Message(DESC, DISTINCT, MINOR,"Hello World!", "22","22","33", "Hello World!"));
        Message message2 = service.log(new Message(ASC, DOUBLES, REGULAR, "Hello World!", "444", "555","555"));
        Message message3 = service.log(new Message(ASC, REGULAR, "Hello World!!"));
//        Message message4 = service.log(new Message(DESC,null));
//        Message message5 = service.log(new Message(DESC));

        service.findByPrimaryKey(message3.getId());
        service.findAll();
        service.findBySeverity(MINOR);
    }
}