import com.tcs.edu.printer.MessageService;

import static com.tcs.edu.decorator.Severity.*;

class Application {
    public static void main(String[] args) {
        MessageService.print(MINOR, "Hello World!");
        MessageService.print(REGULAR, "Hello World!");
        MessageService.print(REGULAR, "Hello World!");
        MessageService.print(MAJOR, "Hello World!");
        MessageService.print(MINOR, "Hello World!");
        MessageService.print(MAJOR, "Hello World!");
    }
}