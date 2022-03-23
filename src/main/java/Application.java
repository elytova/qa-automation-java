import com.tcs.edu.printer.ConsolePrinter;
import com.tcs.edu.decorator.MessageWithTimeDecorator;

class Application {
    public static void main(String[] args) {
        String message = MessageWithTimeDecorator.addTimetoMessage("Hello World!");
        ConsolePrinter.print(message);
    }
}