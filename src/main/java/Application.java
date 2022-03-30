import com.tcs.edu.printer.ConsolePrinter;
import com.tcs.edu.decorator.TimestampMessageDecorator;

class Application {
    public static void main(String[] args) {
        String message = TimestampMessageDecorator.decorate("Hello World!");
        ConsolePrinter.print(message);
    }
}