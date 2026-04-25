import com.zeroc.Ice.*;

public class Server {

    static class PrinterI implements Demo.Printer {
        public String printString(String s, Current current) {
            System.out.println(s);
            return s;
        }

        // Novo
        public String printUpperCase(String s, Current current) {
            System.out.println(s.toUpperCase());
            return s.toUpperCase();
        }
    }

    static class CalculatorI implements Demo.Calculator {
        public int soma(int a, int b, Current current)        { return a + b; }
        public int subtrair(int a, int b, Current current)   { return a - b; }
        public int multiplicar(int a, int b, Current current) { return a * b; }
    }

    public static void main(String[] args) {
        try (Communicator communicator = Util.initialize(args)) {

            ObjectAdapter adapter = communicator.createObjectAdapterWithEndpoints("SimpleAdapter", "default -p 5678");

            adapter.add(new PrinterI(),    Util.stringToIdentity("SimplePrinter"));
            adapter.add(new CalculatorI(), Util.stringToIdentity("SimpleCalculator"));

            adapter.activate();
            communicator.waitForShutdown();
        }
    }
}
