import com.tcs.edu.printer.MessageService;

import static com.tcs.edu.decorator.Severity.*;
import static com.tcs.edu.printer.MessageOrder.*;

class Application {
    public static void main(String[] args) {
        MessageService.print(MINOR, DESC, "Hello World!", "111", "333","222");
        MessageService.print(REGULAR, ASC, "Hello World!", "444", "555");
        MessageService.print(REGULAR, ASC, "Hello World!");
        MessageService.print(MAJOR, "Hello World!");
        MessageService.print(MINOR, "Hello World!");
    }
}