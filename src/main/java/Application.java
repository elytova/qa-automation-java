import com.tcs.edu.printer.ConsolePrinter;

import static com.tcs.edu.decorator.Severity.*;

class Application {
    public static void main(String[] args) {
            ConsolePrinter.printMessage(MINOR, "Hello World!");
            ConsolePrinter.printMessage(REGULAR, "Hello World!");
            ConsolePrinter.printMessage(REGULAR, "Hello World!");
            ConsolePrinter.printMessage(MAJOR, "Hello World!");
            ConsolePrinter.printMessage(MINOR, "Hello World!");
            ConsolePrinter.printMessage(MAJOR, "Hello World!");
    }
}