package com.tinkoff.edu;

import com.tcs.edu.decorator.*;
import com.tcs.edu.domain.*;
import com.tcs.edu.enums.Severity;
import com.tcs.edu.printer.ConsolePrinter;
import com.tcs.edu.printer.OrderedDistinctMessageService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static com.tcs.edu.enums.MessageOrder.*;
import static com.tcs.edu.enums.Doubling.*;
import static org.assertj.core.api.Assertions.*;


@DisplayName("Тестирование формирования/декорирования сообщений")
public class ApplicationTest {

    MessageService service = new OrderedDistinctMessageService(
            new PageDecorator(),
            new OrderDecorator(),
            new DuplicatesDecorator(),
            new SeverityDecorator(),
            new ConsolePrinter()
    );
    @Nested
    @DisplayName("Order of messages")
    class CheckMessageOrder {

        @Test
        @DisplayName("Проверка сохранения сообщения в обычном порядке")
        public void ShouldSaveMessageWithDuplicates() {
            //Given
            Message testMessage = new Message(ASC, "Hello World!", "444", "555", "555");
            //When
            service.log(testMessage);
            //Then
            assertThat(service.findAll().toString())
                    .isEqualTo("[Message{body=[Hello World! (), 444 (), 555 (), 555 ()]}]");
        }

        @Test
        @DisplayName("Проверка сохранения сообщения в обратном порядке")
        public void ShouldSaveMessageInDescOrder() {
            Message testMessage = new Message(DESC, "Hello World!", "22", "22", "33", "Hello World!");
            service.log(testMessage);
            assertThat(service.findAll().toString())
                    .isEqualTo("[Message{body=[Hello World! (), 33 (), 22 (), 22 (), Hello World! ()]}]");
        }

        @Test
        @DisplayName("Проверка дефолтного значения Doubling = ASC")
        public void ShouldSaveMessageInDescOrderWhenOrderIsEmpty() {
            Message testMessage = new Message(DOUBLES, "Hello World!", "22", "22", "33", "Hello World!");
            service.log(testMessage);
            assertThat(service.findAll().toString())
                    .isEqualTo("[Message{body=[Hello World! (), 22 (), 22 (), 33 (), Hello World! ()]}]");
        }
    }

    @Nested
    @DisplayName("Duplication of messages")
    class CheckMessageDuplicates {

        @Test
        @DisplayName("Проверка удаления дублей в сообщении")
        public void ShouldSaveMessageWithoutDuplicates() {
            Message testMessage = new Message(ASC, DISTINCT, "Hello World!", "444", "555", "555");
            service.log(testMessage);
            assertThat(service.findAll().toString())
                    .isEqualTo("[Message{body=[Hello World! (), 444 (), 555 ()]}]");
        }

        @Test
        @DisplayName("Проверка сохранения сообщения с дублями")
        public void ShouldSaveMessageWithDuplicates() {
            Message testMessage = new Message(ASC, DOUBLES, "Hello World!", "444", "555", "555");
            service.log(testMessage);
            assertThat(service.findAll().toString())
                    .isEqualTo("[Message{body=[Hello World! (), 444 (), 555 (), 555 ()]}]");
        }

        @Test
        @DisplayName("Проверка дефолтного значения MessageOrder = DOUBLES")
        public void ShouldSaveMessageWithoutDuplicatesWhenDoublingIsEmpty() {
            Message testMessage = new Message(ASC, "Hello World!", "444", "555", "555");
            service.log(testMessage);
            assertThat(service.findAll().toString())
                    .isEqualTo("[Message{body=[Hello World! (), 444 (), 555 (), 555 ()]}]");
        }
    }

    @Nested
    @DisplayName("Severity checks")
    class CheckSeverity {

        @DisplayName("Проверка сохранения сообщения в зависимости от severity")
        @ParameterizedTest()
        @EnumSource(Severity.class)
        public void ShouldDecorateMessageBasedOnSeverity(Severity severity) {
            String expectedMessage = "";
            Message testMessage = new Message(ASC, severity, "Hello World!", "22", "22", "33", "Hello World!");
            switch (severity) {
                case MINOR:
                    expectedMessage = "[Message{body=[Hello World! (), 22 (), 22 (), 33 (), Hello World! ()]}]";
                    break;
                case REGULAR:
                    expectedMessage = "[Message{body=[Hello World! (!), 22 (!), 22 (!), 33 (!), Hello World! (!)]}]";
                    break;
                case MAJOR:
                    expectedMessage = "[Message{body=[Hello World! (!!!), 22 (!!!), 22 (!!!), 33 (!!!), Hello World! (!!!)]}]";
                    break;
            }

            service.log(testMessage);
            assertThat(service.findBySeverity(severity)
                    .stream().findFirst().toString().replaceAll("Optional", ""))
                    .isEqualTo(expectedMessage);
        }
    }

    @Nested
    @DisplayName("Exceptions")
    class CheckExceptions {

        @Test
        @DisplayName("Проверка выбрасывания Exception: null сообщение")
        public void ShouldReturnExceptionWhenNullBody() {
            Message testMessage = new Message(DESC, null);
            assertThatExceptionOfType(RuntimeException.class)
                    .isThrownBy(() -> service.log(testMessage))
                    .havingRootCause()
                    .withMessage("body is null");
        }

        @Test
        @DisplayName("Проверка выбрасывания Exception: empty сообщение")
        public void ShouldReturnExceptionWhenBodyMissing() {
            Message testMessage = new Message(DESC);
            assertThatExceptionOfType(RuntimeException.class)
                    .isThrownBy(() -> service.log(testMessage))
                    .havingRootCause()
                    .withMessage("body is empty");
        }
    }
}
