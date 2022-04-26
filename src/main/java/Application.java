import com.tcs.edu.printer.MessageService;
import static com.tcs.edu.enums.Severity.*;
import static com.tcs.edu.enums.MessageOrder.*;
import static com.tcs.edu.enums.Doubling.*;

class Application {
    public static void main(String[] args) {
        MessageService.print(MINOR, DESC, DISTINCT, "Hello World!", "111", "222","222","333", "Hello World!");
        MessageService.print(REGULAR, ASC, DOUBLES, "Hello World!", "444", "555","555");
        MessageService.print(REGULAR, ASC, "Hello World!");
        MessageService.print(MAJOR, "Hello World!");
        MessageService.print(MINOR, "Hello World!");
    }
}