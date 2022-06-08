package com.tinkoff.edu;

import com.tcs.edu.decorator.*;
import com.tcs.edu.domain.*;
import com.tcs.edu.enums.Severity;
import com.tcs.edu.printer.ConsolePrinter;
import com.tcs.edu.printer.OrderedDistinctMessageService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static com.tcs.edu.enums.MessageOrder.*;
import static com.tcs.edu.enums.Doubling.*;

public class ApplicationTest {

        MessageService service = new OrderedDistinctMessageService(
                new PageDecorator(),
                new OrderDecorator(),
                new DuplicatesDecorator(),
                new SeverityDecorator(),
                new ConsolePrinter()
        );

    @Test
    @DisplayName("Проверка сохранения сообщения в обычном порядке, с дублями")
    public void ShouldSaveMessageWithDuplicates(){
        //Given
        Message testMessage = new Message(ASC, DOUBLES, "Hello World!", "444", "555", "555");
        //When
        service.log(testMessage);
        //Then
        Assertions.assertEquals("[Message{body=[Hello World! (), 444 (), 555 (), 555 ()]}]",
                                    service.findAll().toString());
    }

    @Test
    @DisplayName("Проверка сохранения сообщения в обратном порядке, без дублей")
    public void ShouldSaveMessageInDescOrder(){
        Message testMessage = new Message(DESC, DISTINCT,"Hello World!", "22","22","33", "Hello World!");
        service.log(testMessage);
        Assertions.assertEquals("[Message{body=[Hello World! (), 33 (), 22 ()]}]",
                service.findAll().toString());
    }

    @Test
    @DisplayName("Проверка удаления дублей в сообщении")
    public void ShouldSaveMessageWithoutDuplicates(){
        Message testMessage = new Message(ASC, DISTINCT, "Hello World!", "444", "555", "555");
        service.log(testMessage);
        Assertions.assertEquals("[Message{body=[Hello World! (), 444 (), 555 ()]}]",
                service.findAll().toString());
    }

    @DisplayName("Проверка сохранения сообщения в зависимости от severity")
    @ParameterizedTest()
    @EnumSource(Severity.class)
    public void ShouldDecorateMessageBasedOnSeverity(Severity severity){
        String expectedMessage = "";
        Message testMessage = new Message(ASC, severity,"Hello World!", "22","22","33", "Hello World!");
        switch (severity){
            case MINOR: expectedMessage = "[Message{body=[Hello World! (), 22 (), 22 (), 33 (), Hello World! ()]}]"; break;
            case REGULAR: expectedMessage = "[Message{body=[Hello World! (!), 22 (!), 22 (!), 33 (!), Hello World! (!)]}]"; break;
            case MAJOR: expectedMessage = "[Message{body=[Hello World! (!!!), 22 (!!!), 22 (!!!), 33 (!!!), Hello World! (!!!)]}]"; break;
        }

        service.log(testMessage);
        Assertions.assertEquals(expectedMessage, service.findBySeverity(severity)
                .stream().findFirst().toString().replaceAll("Optional", ""));
    }

    @Test
    @DisplayName("Проверка выбрасывания Exception: null сообщение")
    public void ShouldReturnExceptionWhenNullBody() {
        Message testMessage = new Message(DESC, null);
        Assertions.assertThrows(LogException.class, () -> service.log(testMessage));
    }

    @Test
    @DisplayName("Проверка выбрасывания Exception: empty сообщение")
    public void ShouldReturnExceptionWhenBodyMissing(){
        Message testMessage = new Message(DESC);
        Assertions.assertThrows(LogException.class, () -> service.log(testMessage));
    }
}
