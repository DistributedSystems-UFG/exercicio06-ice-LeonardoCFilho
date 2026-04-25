import com.zeroc.Ice.*;

public class Client {
    public static void main(String[] args) {
        // 1. Initialize the Ice communicator within a try-with-resources block
        try (Communicator communicator = Util.initialize(args)) {

            // 2. Create a proxy to the remote 'Printer' object
            // Replace '10.0.0.5' with the actual IP of your ICE server
            ObjectPrx base = communicator.stringToProxy("SimplePrinter:default -h 127.0.0.1 -p 5678");

            // 3. Downcast the proxy to the Printer interface
            Demo.PrinterPrx printer = Demo.PrinterPrx.checkedCast(base);

            if (printer == null) {
                throw new Error("Invalid proxy");
            }

            // 4. Call the remote method
            String response = printer.printString("Hello from Goiania!");
            System.out.println("Server responded: " + response);

            // Novos métodos do Printer
            String upper = printer.printUpperCase("hello from goiania!");
            System.out.println("Upper: " + upper);

            // Conectar ao Calculator
            ObjectPrx baseCalc = communicator.stringToProxy("SimpleCalculator:default -h 127.0.0.1 -p 5678");
            Demo.CalculatorPrx calculator = Demo.CalculatorPrx.checkedCast(baseCalc);

            if (calculator == null) {
                throw new Error("Invalid proxy");
            }

            System.out.println("Soma: "          + calculator.soma(25, -14));
            System.out.println("Subtracao: "     + calculator.subtrair(25, -14));
            System.out.println("Multiplicacao: " + calculator.multiplicar(25, -14));

        } catch (LocalException e) {
            e.printStackTrace();
        }
    }
}
