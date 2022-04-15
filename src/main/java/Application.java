import com.tcs.edu.printer.ConsolePrinter;

import static com.tcs.edu.decorator.Severity.*;

class Application {
    public static void main(String[] args) {
            ConsolePrinter.print(MINOR, "Hello World!");
            ConsolePrinter.print(REGULAR, "Hello World!");
            ConsolePrinter.print(REGULAR, "Hello World!");
            ConsolePrinter.print(MAJOR, "Hello World!");
            ConsolePrinter.print(MINOR, "Hello World!");
            ConsolePrinter.print(MAJOR, "Hello World!");
    }
}