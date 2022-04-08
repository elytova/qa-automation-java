import com.tcs.edu.printer.ConsolePrinter;
import com.tcs.edu.decorator.TimestampMessageDecorator;

import static com.tcs.edu.decorator.TimestampMessageDecorator.messageCount;

class Application {
    public static void main(String[] args) {
        for (int i = 0; i < 6; i++) {
            String message = TimestampMessageDecorator.decorate("Hello World!");
            ConsolePrinter.print(message);
        }
    }
}